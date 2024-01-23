package br.com.fiap.mikes.application.inbound.orderpayment.dto

import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.UpdatedOrderPaymentInboundRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UpdatedOrderPaymentInboundRequestTest {
    @Test
    fun `should create UpdatedOrderPaymentInboundRequest with orderId and status`() {
        val orderId = "ABC123"
        val status = "SUCCESS"
        val request = UpdatedOrderPaymentInboundRequest(orderId, status)

        assertEquals(orderId, request.orderId)
        assertEquals(status, request.status)
    }

    @Test
    fun `should have correct string representation`() {
        val orderId = "XYZ789"
        val status = "FAILURE"
        val request = UpdatedOrderPaymentInboundRequest(orderId, status)

        val expectedString = "UpdatedOrderPaymentInboundRequest(orderId=$orderId, status=$status)"
        assertEquals(expectedString, request.toString())
    }

    @Test
    fun `should be equal to another UpdatedOrderPaymentInboundRequest with the same orderId and status`() {
        val orderId = "123DEF"
        val status = "PENDING"
        val request1 = UpdatedOrderPaymentInboundRequest(orderId, status)
        val request2 = UpdatedOrderPaymentInboundRequest(orderId, status)

        assertEquals(request1, request2)
    }

    @Test
    fun `should have the same hashCode for two UpdatedOrderPaymentInboundRequest instances with the same orderId and status`() {
        val orderId = "456GHI"
        val status = "SUCCESS"
        val request1 = UpdatedOrderPaymentInboundRequest(orderId, status)
        val request2 = UpdatedOrderPaymentInboundRequest(orderId, status)

        assertEquals(request1.hashCode(), request2.hashCode())
    }
}
