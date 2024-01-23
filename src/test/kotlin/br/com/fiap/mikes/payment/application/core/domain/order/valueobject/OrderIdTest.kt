package br.com.fiap.mikes.payment.application.core.domain.order.valueobject

import br.com.fiap.mikes.payment.application.core.domain.exception.order.InvalidOrderIdException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrderIdTest {
    @Test
    fun `new should create OrderId with valid UUID`() {
        val uuid = "550e8400-e29b-41d4-a716-446655440000"
        val result = OrderId.new(uuid)

        assertTrue(result.isSuccess)
        assertEquals(uuid, result.getOrThrow().value)
    }

    @Test
    fun `new should fail with invalid UUID`() {
        val invalidUuid = "invalid-uuid"
        val result = OrderId.new(invalidUuid)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is InvalidOrderIdException)
        assertEquals("invalid order id.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `generate should create a new random OrderId`() {
        val orderId1 = OrderId.generate()
        val orderId2 = OrderId.generate()

        assertTrue(orderId1 != orderId2)
    }
}
