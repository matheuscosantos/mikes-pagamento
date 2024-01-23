package br.com.fiap.mikes.payment.application.core.domain.exception.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidValueException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InvalidOrderPaymentIdExceptionTest {
    @Test
    fun `should create InvalidOrderPaymentIdException with correct message`() {
        val errorMessage = "Invalid OrderPaymentId"
        val exception = InvalidOrderPaymentIdException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderPaymentId", exception.type)
    }

    @Test
    fun `should be a subclass of InvalidValueException`() {
        val exception = InvalidOrderPaymentIdException("Some message")

        assertTrue(exception is InvalidValueException)
    }
}
