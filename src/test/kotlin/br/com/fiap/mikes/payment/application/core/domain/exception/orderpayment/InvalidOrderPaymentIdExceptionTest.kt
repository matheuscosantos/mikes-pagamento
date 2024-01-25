package br.com.fiap.mikes.payment.application.core.domain.exception.orderpayment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidOrderPaymentIdExceptionTest {
    @Test
    fun `should create InvalidOrderPaymentIdException with correct message`() {
        val errorMessage = "Invalid OrderPaymentId"
        val exception = InvalidOrderPaymentIdException(errorMessage)

        assertEquals(errorMessage, exception.message)
        assertEquals("OrderPaymentId", exception.type)
    }
}
