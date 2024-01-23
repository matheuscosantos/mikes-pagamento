package br.com.fiap.mikes.payment.application.core.domain.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

class OrderPaymentTest {
    @Test
    fun `new should create a new OrderPayment with unique id`() {
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea").getOrThrow()
        val orderPaymentStatus = OrderPaymentStatus.REFUSED
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val result = OrderPayment.new(orderId, orderPaymentStatus, createdAt, updatedAt)
        val orderPayment = result.getOrThrow()

        assertNotEquals(UUID(0, 0), orderPayment.id)
        assertEquals(orderId, orderPayment.orderId)
        assertEquals(orderPaymentStatus, orderPayment.orderPaymentStatus)
        assertEquals(createdAt, orderPayment.createdAt)
        assertEquals(updatedAt, orderPayment.updatedAt)
    }

    @Test
    fun `payment should update OrderPayment status to ACCEPTED when paid is true`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea").getOrThrow()
        val orderPaymentStatus = OrderPaymentStatus.REFUSED
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPayment =
            OrderPayment(
                id,
                orderId,
                orderPaymentStatus,
                createdAt,
                updatedAt,
            )

        val updatedOrderPayment = orderPayment.payment(true)

        assertEquals(
            orderId,
            updatedOrderPayment.orderId,
        )
        assertEquals(
            OrderPaymentStatus.ACCEPTED,
            updatedOrderPayment.orderPaymentStatus,
        )
        assertEquals(
            createdAt,
            updatedOrderPayment.createdAt,
        )
        assertNotEquals(
            updatedAt,
            updatedOrderPayment.updatedAt,
        )
    }

    @Test
    fun `payment should update OrderPayment status to REFUSED when paid is false`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = OrderId.new("53c58b9a-2222-4122-8205-a9a571e807ea").getOrThrow()
        val orderPaymentStatus = OrderPaymentStatus.REFUSED
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPayment =
            OrderPayment(
                id,
                orderId,
                orderPaymentStatus,
                createdAt,
                updatedAt,
            )

        val updatedOrderPayment = orderPayment.payment(false)

        assertEquals(orderId, updatedOrderPayment.orderId)
        assertEquals(OrderPaymentStatus.REFUSED, updatedOrderPayment.orderPaymentStatus)
        assertEquals(createdAt, updatedOrderPayment.createdAt)
        assertNotEquals(updatedAt, updatedOrderPayment.updatedAt)
    }
}
