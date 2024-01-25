package br.com.fiap.mikes.payment.application.core.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidOrderPaymentStatusExceptionTest {
    @Test
    fun `should create InvalidOrderPaymentStatusException with correct message`() {
        val errorMessage = "Invalid OrderPaymentStatus"
        val exception = InvalidOrderPaymentStatusException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderPaymentStatus", exception.type)
    }
}
