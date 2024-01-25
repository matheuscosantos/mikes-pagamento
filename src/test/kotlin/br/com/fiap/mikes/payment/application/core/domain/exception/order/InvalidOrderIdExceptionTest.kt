package br.com.fiap.mikes.payment.application.core.domain.exception.order

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidOrderIdExceptionTest {
    @Test
    fun `should create InvalidOrderIdException with correct message`() {
        val errorMessage = "Invalid OrderId"
        val exception = InvalidOrderIdException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderId", exception.type)
    }
}
