package br.com.fiap.mikes.application.inbound.orderpayment.dto

import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewOrderPaymentInboundRequestTest {
    @Test
    fun `should create NewOrderPaymentInboundRequest with orderId`() {
        val orderId = "ABC123"
        val request = NewOrderPaymentInboundRequest(orderId)

        assertEquals(orderId, request.orderId)
    }

    @Test
    fun `should have correct string representation`() {
        val orderId = "XYZ789"
        val request = NewOrderPaymentInboundRequest(orderId)

        val expectedString = "NewOrderPaymentInboundRequest(orderId=$orderId)"
        assertEquals(expectedString, request.toString())
    }

    @Test
    fun `should be equal to another NewOrderPaymentInboundRequest with the same orderId`() {
        val orderId = "123DEF"
        val request1 = NewOrderPaymentInboundRequest(orderId)
        val request2 = NewOrderPaymentInboundRequest(orderId)

        assertEquals(request1, request2)
    }

    @Test
    fun `should have the same hashCode for two NewOrderPaymentInboundRequest instances with the same orderId`() {
        val orderId = "456GHI"
        val request1 = NewOrderPaymentInboundRequest(orderId)
        val request2 = NewOrderPaymentInboundRequest(orderId)

        assertEquals(request1.hashCode(), request2.hashCode())
    }
}
