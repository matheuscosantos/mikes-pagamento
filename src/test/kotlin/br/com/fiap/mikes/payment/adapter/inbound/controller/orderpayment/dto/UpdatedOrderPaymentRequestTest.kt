package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

class UpdatedOrderPaymentRequestTest {
    @Test
    fun `from should map OrderPayment to UpdatedOrderPaymentRequest`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea")
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPayment =
            OrderPayment(
                id = id,
                orderId = orderId.getOrThrow(),
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val updatedOrderPaymentRequest = UpdatedOrderPaymentRequest.from(orderPayment)

        assertEquals(orderId.getOrThrow().value, updatedOrderPaymentRequest.orderId)
        assertEquals(orderPaymentStatus.value, updatedOrderPaymentRequest.orderPaymentStatus)
    }

    @Test
    fun `from should use current timestamp as updatedAt if not provided in OrderPayment`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea")
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED
        val createdAt = LocalDateTime.now().minusDays(1)

        val orderPayment =
            OrderPayment(
                id = id,
                orderId = orderId.getOrThrow(),
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = LocalDateTime.now(),
            )

        val updatedOrderPaymentRequest = UpdatedOrderPaymentRequest.from(orderPayment)

        assertEquals(orderId.getOrThrow().value, updatedOrderPaymentRequest.orderId)
        assertEquals(orderPaymentStatus.value, updatedOrderPaymentRequest.orderPaymentStatus)
    }

    @Test
    fun `from should use provided updatedAt from OrderPayment`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea")
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED
        val createdAt = LocalDateTime.now().minusDays(1)
        val updatedAt = LocalDateTime.now().minusHours(2)

        val orderPayment =
            OrderPayment(
                id = id,
                orderId = orderId.getOrThrow(),
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val updatedOrderPaymentRequest = UpdatedOrderPaymentRequest.from(orderPayment)

        assertEquals(orderId.getOrThrow().value, updatedOrderPaymentRequest.orderId)
        assertEquals(orderPaymentStatus.value, updatedOrderPaymentRequest.orderPaymentStatus)
    }
}
