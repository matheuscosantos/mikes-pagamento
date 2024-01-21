package br.com.fiap.mikes.payment.application.core.usecase.orderpayment

import br.com.fiap.mikes.payment.adapter.outbound.database.exception.FailToSave
import br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client.SnsOrderReceivedMessenger
import br.com.fiap.mikes.payment.application.core.domain.exception.order.InvalidOrderIdException
import br.com.fiap.mikes.payment.application.core.domain.exception.order.OrderPaymentAlreadyExistsException
import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.usecase.exception.orderpayment.InvalidOrderPaymentStateException
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.UpdatedOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.mapper.orderpayment.OrderPaymentDomainMapper
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import br.com.fiap.mikes.payment.application.port.outbound.order.dto.PaymentReceivedMessage
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.OrderPaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ProcessOrderPaymentUseCase(
    private val orderPaymentRepository: OrderPaymentRepository,
    private val orderPaymentDomainMapper: OrderPaymentDomainMapper,
    private val snsMessenger: SnsOrderReceivedMessenger,
) : ProcessOrderPaymentService {
    override fun create(newOrderPaymentInboundRequest: NewOrderPaymentInboundRequest): Result<OrderPayment> {
        try {
            val orderId =
                OrderId.new(
                    newOrderPaymentInboundRequest.orderId,
                ).getOrElse {
                    throw InvalidOrderIdException("Invalid orderId.")
                }
            val existingOrderPayment =
                orderPaymentRepository.findByOrderId(
                    orderId,
                )

            if (existingOrderPayment != null) {
                return Result.failure(
                    OrderPaymentAlreadyExistsException(
                        "Payment already exists for orderId: $orderId",
                    ),
                )
            }

            val newOrderPayment =
                OrderPayment.new(
                    orderId,
                    OrderPaymentStatus.WAITING,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                )

            val savedOrderPayment =
                orderPaymentRepository.save(
                    newOrderPayment.getOrElse {
                        throw FailToSave("Fail to save")
                    },
                )
            return orderPaymentDomainMapper.new(savedOrderPayment)
        } catch (ex: InvalidOrderIdException) {
            return Result.failure(ex)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    override fun update(updatedOrderPaymentInboundRequest: UpdatedOrderPaymentInboundRequest): Result<UpdatedOrderPaymentInboundRequest> {
        return try {
            val orderPaymentStatus =
                OrderPaymentStatus.findByValue(
                    updatedOrderPaymentInboundRequest.status,
                ).getOrElse {
                    throw InvalidOrderPaymentStateException("Invalid status.")
                }

            val orderId =
                OrderId.new(
                    updatedOrderPaymentInboundRequest.orderId,
                ).getOrElse {
                    throw InvalidOrderIdException("Invalid OrderId")
                }

            orderPaymentRepository.updateStatusByOrderId(
                orderId,
                orderPaymentStatus,
            )
            snsMessenger.send(
                PaymentReceivedMessage(
                    orderId.value,
                    orderPaymentStatus.value,
                ),
            )
            Result.success(updatedOrderPaymentInboundRequest)
        } catch (e: Exception) {
            Result.failure(OrderPaymentAlreadyExistsException("{$e})"))
        }
    }
}
