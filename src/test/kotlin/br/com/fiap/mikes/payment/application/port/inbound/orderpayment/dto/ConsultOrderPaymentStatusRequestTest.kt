package br.com.fiap.mikes.payment.application.port.inbound.orderpayment.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConsultOrderPaymentStatusRequestTest {
    @Test
    fun `creating ConsultOrderPaymentStatusRequest should initialize orderId correctly`() {
        val orderId = "someOrderId"
        val request = ConsultOrderPaymentStatusRequest(orderId)
        assertEquals(orderId, request.orderId)
    }

    @Test
    fun `two ConsultOrderPaymentStatusRequest instances with the same orderId should be equal`() {
        val orderId = "someOrderId"
        val request1 = ConsultOrderPaymentStatusRequest(orderId)
        val request2 = ConsultOrderPaymentStatusRequest(orderId)
        assertEquals(request1, request2)
    }

    @Test
    fun `ConsultOrderPaymentStatusRequest instances with different orderId should not be equal`() {
        val orderId1 = "orderId1"
        val orderId2 = "orderId2"
        val request1 = ConsultOrderPaymentStatusRequest(orderId1)
        val request2 = ConsultOrderPaymentStatusRequest(orderId2)
        assert(request1 != request2)
    }
}
