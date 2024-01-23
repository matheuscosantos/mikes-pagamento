package br.com.fiap.mikes.payment.application.core.domain.exception.order

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidValueException
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

    @Test
    fun `should be a subclass of InvalidValueException`() {
        val exception = OrderPaymentAlreadyExistsException("Some message")

        assertTrue(exception is InvalidValueException)
    }
}
