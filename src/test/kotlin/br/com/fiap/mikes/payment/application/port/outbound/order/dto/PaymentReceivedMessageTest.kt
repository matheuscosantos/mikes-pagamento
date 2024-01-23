package br.com.fiap.mikes.payment.application.port.outbound.order.dto

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PaymentReceivedMessageTest {
    private val objectMapper = ObjectMapper()

    @Test
    fun `should serialize and deserialize PaymentReceivedMessage correctly`() {
        val originalMessage = PaymentReceivedMessage(orderId = "123456", status = "PAID")

        val json = objectMapper.writeValueAsString(originalMessage)
        val deserializedMessage = objectMapper.readValue(json, PaymentReceivedMessage::class.java)

        assertEquals(originalMessage, deserializedMessage)
    }
}
