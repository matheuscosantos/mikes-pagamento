package br.com.fiap.mikes.payment.application.core.usecase.exception.orderpayment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidOrderPaymentStateExceptionTest {
    @Test
    fun `should create InvalidOrderPaymentStateException with correct message`() {
        val errorMessage = "Invalid OrderPayment state"
        val exception = InvalidOrderPaymentStateException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderPayment", exception.type)
    }
}
