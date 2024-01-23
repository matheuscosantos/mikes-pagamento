package br.com.fiap.mikes.payment.application.core.domain.exception.order

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidValueException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InvalidOrderIdExceptionTest {
    @Test
    fun `should create InvalidOrderIdException with correct message`() {
        val errorMessage = "Invalid OrderId"
        val exception = InvalidOrderIdException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderId", exception.type)
    }

    @Test
    fun `should be a subclass of InvalidValueException`() {
        val exception = InvalidOrderIdException("Some message")

        assertTrue(exception is InvalidValueException)
    }
}
