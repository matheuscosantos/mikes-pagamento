package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewOrderPaymentRequestTest {
    @Test
    fun `toInbound should map NewOrderPaymentRequest to NewOrderPaymentInboundRequest`() {
        val orderId = "12345"
        val newOrderPaymentRequest = NewOrderPaymentRequest(orderId)
        val newOrderPaymentInboundRequest = newOrderPaymentRequest.toInbound()

        assertEquals(orderId, newOrderPaymentInboundRequest.orderId)
    }
}
