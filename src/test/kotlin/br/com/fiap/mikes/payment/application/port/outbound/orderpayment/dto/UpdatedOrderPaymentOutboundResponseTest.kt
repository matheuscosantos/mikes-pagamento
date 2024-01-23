package br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdatedOrderPaymentOutboundResponseTest {
    @Test
    fun `creating UpdatedOrderPaymentOutboundResponse should initialize properties correctly`() {
        val id = "someId"
        val orderId = "someOrderId"
        val orderPaymentStatus = "PAID"
        val createdAt = LocalDateTime.now()
        val updatedAt = LocalDateTime.now()

        val response = UpdatedOrderPaymentOutboundResponse(id, orderId, orderPaymentStatus, createdAt, updatedAt)

        assertEquals(id, response.id)
        assertEquals(orderId, response.orderId)
        assertEquals(orderPaymentStatus, response.orderPaymentStatus)
        assertEquals(createdAt, response.createdAt)
        assertEquals(updatedAt, response.updatedAt)
    }

    @Test
    fun `updating orderPaymentStatus should modify orderPaymentStatus property`() {
        val response = UpdatedOrderPaymentOutboundResponse("someId", "someOrderId", "PENDING", LocalDateTime.now(), LocalDateTime.now())
        val newOrderPaymentStatus = "PAID"
        response.orderPaymentStatus = newOrderPaymentStatus
        assertEquals(newOrderPaymentStatus, response.orderPaymentStatus)
    }
}
