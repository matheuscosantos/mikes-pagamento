package br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client

import br.com.fiap.mikes.payment.application.port.outbound.order.dto.PaymentReceivedMessage
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SnsOrderReceivedMessengerTest {
    private lateinit var snsMessenger: SnsMessenger
    private lateinit var snsOrderReceivedMessenger: SnsOrderReceivedMessenger

    @BeforeEach
    fun setUp() {
        snsMessenger = mockk(relaxed = true)
        snsOrderReceivedMessenger = SnsOrderReceivedMessenger(snsMessenger, "test-topic")
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `send should call snsMessenger send with correct arguments`() {
        val paymentReceivedMessage = PaymentReceivedMessage("orderId", "status")
        snsOrderReceivedMessenger.send(paymentReceivedMessage)
        verify(exactly = 1) { snsMessenger.send("test-topic", paymentReceivedMessage) }
    }
}
