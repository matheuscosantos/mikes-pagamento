package br.com.fiap.mikes.payment.adapter.outbound.database.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FailToSaveTest {
    @Test
    fun `FailToSave should have correct type`() {
        val failToSave = FailToSave("Error")
        assertEquals("Order", failToSave.type)
    }

    @Test
    fun `FailToSave should have correct message`() {
        val errorMessage = "Failed to save order"
        val failToSave = FailToSave(errorMessage)
        assertEquals(errorMessage, failToSave.message)
    }
}
