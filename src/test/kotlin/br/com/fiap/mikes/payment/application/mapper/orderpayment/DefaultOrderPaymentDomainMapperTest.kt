package br.com.fiap.mikes.payment.application.mapper.orderpayment

import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

class DefaultOrderPaymentDomainMapperTest {
    private val mapper = DefaultOrderPaymentDomainMapper()

    @Test
    fun `new should map OrderPaymentOutboundResponse to OrderPayment`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = "53c58b9a-2222-4122-8205-a9a571e807ea"
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED.value
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T13:00:00")

        val outboundResponse =
            OrderPaymentOutboundResponse(
                id = id,
                orderId = orderId,
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val result = mapper.new(outboundResponse)

        assertTrue(result.isSuccess)
        val orderPayment = result.getOrThrow()

        assertEquals(OrderPaymentStatus.ACCEPTED, orderPayment.orderPaymentStatus)
        assertEquals(LocalDateTime.parse("2022-01-01T12:00:00"), orderPayment.createdAt)
        assertEquals(LocalDateTime.parse("2022-01-01T13:00:00"), orderPayment.updatedAt)
    }

    @Test
    fun `new should handle invalid orderId`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val invalidOrderId = "invalid-order-id"
        val orderPaymentStatus = "PAID"
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T13:00:00")

        val outboundResponse =
            OrderPaymentOutboundResponse(
                id = id,
                orderId = invalidOrderId,
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val result = mapper.new(outboundResponse)

        assertTrue(result.isFailure)
    }

    @Test
    fun `new should handle invalid orderPaymentStatus`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = "12345"
        val invalidOrderPaymentStatus = "INVALID_STATUS"
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T13:00:00")

        val outboundResponse =
            OrderPaymentOutboundResponse(
                id = id,
                orderId = orderId,
                orderPaymentStatus = invalidOrderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val result = mapper.new(outboundResponse)

        assertTrue(result.isFailure)
    }
}
