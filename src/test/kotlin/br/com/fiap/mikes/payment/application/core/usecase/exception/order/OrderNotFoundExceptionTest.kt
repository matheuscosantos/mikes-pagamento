package br.com.fiap.mikes.payment.application.core.usecase.exception.order

import br.com.fiap.mikes.payment.application.core.usecase.exception.NotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrderNotFoundExceptionTest {
    @Test
    fun `should create OrderNotFoundException with correct message`() {
        val errorMessage = "Order not found"
        val exception = OrderNotFoundException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("Order", exception.type)
    }

    @Test
    fun `should be a subclass of NotFoundException`() {
        val exception = OrderNotFoundException("Some message")

        assertTrue(exception is NotFoundException)
    }
}
