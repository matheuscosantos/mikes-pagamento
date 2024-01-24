package br.com.fiap.mikes.payment.application.core.usecase.orderpayment

import br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client.SnsOrderReceivedMessenger
import br.com.fiap.mikes.payment.application.core.domain.exception.order.InvalidOrderIdException
import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.mapper.orderpayment.OrderPaymentDomainMapper
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.OrderPaymentRepository
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

class ProcessOrderPaymentUseCaseTest {
    @Test
    fun `create should return success result when everything is valid`() {
        val orderPaymentRepository = mockk<OrderPaymentRepository>()
        val orderPaymentDomainMapper = mockk<OrderPaymentDomainMapper>()
        val snsMessenger = mockk<SnsOrderReceivedMessenger>()
        val processOrderPaymentUseCase = ProcessOrderPaymentUseCase(orderPaymentRepository, orderPaymentDomainMapper, snsMessenger)

        val orderIdValue = "e7f3a7ab-1111-480c-b77f-eefca352a942"
        val newOrderPaymentInboundRequest = NewOrderPaymentInboundRequest(orderId = orderIdValue)
        val id = UUID.fromString("e7f3a7ab-2222-480c-b77f-eefca352a942")
        val orderId =
            OrderId.new(orderIdValue).getOrElse {
                throw InvalidOrderIdException("Invalid orderId.")
            }
        val existingOrderPayment = null
        every { orderPaymentRepository.findByOrderId(orderId) } returns existingOrderPayment

        val orderPaymentOutboundResponse =
            OrderPaymentOutboundResponse(
                id,
                orderId.value,
                "",
                LocalDateTime.parse("2022-01-01T12:00:00"),
                LocalDateTime.parse("2022-01-01T12:00:00"),
            )
        every { orderPaymentRepository.save(any()) } returns orderPaymentOutboundResponse
        val savedOrderPayment = OrderPayment(id, orderId, OrderPaymentStatus.WAITING, LocalDateTime.now(), LocalDateTime.now())
        every { orderPaymentDomainMapper.new(orderPaymentOutboundResponse) } returns Result.success(savedOrderPayment)

        val result = processOrderPaymentUseCase.create(newOrderPaymentInboundRequest)

        verify(exactly = 1) { orderPaymentRepository.findByOrderId(orderId) }
        verify(exactly = 1) { orderPaymentRepository.save(any()) }
        verify(exactly = 1) { orderPaymentDomainMapper.new(orderPaymentOutboundResponse) }
        assertEquals(Result.success(savedOrderPayment), result)
    }
}
