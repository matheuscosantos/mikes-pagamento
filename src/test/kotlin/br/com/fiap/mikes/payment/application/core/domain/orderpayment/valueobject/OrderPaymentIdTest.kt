package br.com.fiap.mikes.payment.application.core.domain.orderpayment.valueobject

import br.com.fiap.mikes.payment.application.core.domain.exception.orderpayment.InvalidOrderPaymentIdException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrderPaymentIdTest {
    @Test
    fun `new should create OrderPaymentId with valid UUID`() {
        val validUuid = "550e8400-e29b-41d4-a716-446655440000"
        val result = OrderPaymentId.new(validUuid)

        assertTrue(result.isSuccess)
        assertEquals(validUuid, result.getOrThrow().value)
    }

    @Test
    fun `new should fail with invalid UUID`() {
        val invalidUuid = "invalid-uuid"
        val result = OrderPaymentId.new(invalidUuid)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is InvalidOrderPaymentIdException)
        assertEquals("invalid order payment id.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `generate should create a new random OrderPaymentId`() {
        val id1 = OrderPaymentId.generate()
        val id2 = OrderPaymentId.generate()

        assertTrue(id1 != id2) // Very unlikely to have the same UUIDs generated
    }
}
