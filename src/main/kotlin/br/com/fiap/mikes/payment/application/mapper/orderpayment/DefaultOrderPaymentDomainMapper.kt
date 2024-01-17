package br.com.fiap.mikes.payment.application.mapper.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.valueobject.OrderPaymentId
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse
import org.springframework.stereotype.Service

@Service
class DefaultOrderPaymentDomainMapper : OrderPaymentDomainMapper {
    override fun new(orderPaymentOutboundResponse: OrderPaymentOutboundResponse): Result<OrderPayment> =
        with(orderPaymentOutboundResponse) {
            val id = OrderPaymentId.new(id).getOrElse { return Result.failure(it) }
            val orderId = OrderId.new(orderId).getOrElse { return Result.failure(it) }
            val status = OrderPaymentStatus.findByValue(orderPaymentStatus).getOrElse { return Result.failure(it) }

            return OrderPayment.new(
                id,
                orderId,
                status,
                createdAt,
                updatedAt,
            )
        }
}
