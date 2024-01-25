package br.com.fiap.mikes.payment.application.core.domain.exception.order

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrderPaymentAlreadyExistsExceptionTest {
    @Test
    fun `should create OrderPaymentAlreadyExistsException with correct message`() {
        val errorMessage = "OrderPayment already exists"
        val exception = OrderPaymentAlreadyExistsException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderId", exception.type)
    }
}
