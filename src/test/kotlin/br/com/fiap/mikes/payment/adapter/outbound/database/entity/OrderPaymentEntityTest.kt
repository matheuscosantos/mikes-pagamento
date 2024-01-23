package br.com.fiap.mikes.payment.adapter.outbound.database.entity

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class OrderPaymentEntityTest {
    @Test
    fun `from should map OrderPayment to OrderPaymentEntity`() {
        val id = UUID.fromString("53c58b9a-1111-4122-8205-a9a571e807ea")
        val orderId = "53c58b9a-2222-4122-8205-a9a571e807ea"
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPayment =
            OrderPayment(
                id = id,
                orderId = OrderId.new(orderId).getOrThrow(),
                orderPaymentStatus = orderPaymentStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val orderPaymentEntity = OrderPaymentEntity.from(orderPayment)

        assertNotNull(orderPaymentEntity.id)
        assertEquals(orderId, orderPaymentEntity.orderId)
        assertEquals(orderPaymentStatus.value, orderPaymentEntity.status)
        assertEquals(createdAt, orderPaymentEntity.createdAt)
        assertEquals(updatedAt, orderPaymentEntity.updatedAt)
    }

    @Test
    fun `toOutbound should map OrderPaymentEntity to OrderPaymentOutboundResponse`() {
        val orderId = "12345"
        val status = "PAID"
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPaymentEntity =
            OrderPaymentEntity(
                id = UUID.randomUUID(),
                orderId = orderId,
                status = status,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val orderPaymentOutboundResponse = orderPaymentEntity.toOutbound()

        assertEquals(orderPaymentEntity.id, orderPaymentOutboundResponse.id)
        assertEquals(orderId, orderPaymentOutboundResponse.orderId)
        assertEquals(status, orderPaymentOutboundResponse.orderPaymentStatus)
        assertEquals(createdAt, orderPaymentOutboundResponse.createdAt)
        assertEquals(updatedAt, orderPaymentOutboundResponse.updatedAt)
    }

    @Test
    fun `updateStatus should update status and updatedAt`() {
        val orderId = "12345"
        val initialStatus = "PENDING"
        val createdAt = LocalDateTime.parse("2022-01-01T12:00:00")
        val updatedAt = LocalDateTime.parse("2022-01-01T12:00:00")

        val orderPaymentEntity =
            OrderPaymentEntity(
                id = UUID.randomUUID(),
                orderId = orderId,
                status = initialStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )

        val newStatus = "PAID"

        orderPaymentEntity.updateStatus(newStatus)

        assertEquals(newStatus, orderPaymentEntity.status)
        assert(orderPaymentEntity.updatedAt.isAfter(updatedAt))
    }
}
