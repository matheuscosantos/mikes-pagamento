package br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client

import io.awspring.cloud.sns.core.SnsTemplate
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.messaging.support.GenericMessage

class DefaultSnsMessengerTest {
    private lateinit var snsTemplate: SnsTemplate
    private lateinit var snsMessenger: DefaultSnsMessenger

    @BeforeEach
    fun setUp() {
        snsTemplate = mockk(relaxed = true)
        snsMessenger = DefaultSnsMessenger(snsTemplate)
    }

    @Test
    fun `send should send message to SNS topic`() {
        val topic = "example-topic"
        val messageContent = "Hello, SNS!"

        snsMessenger.send(topic, messageContent)

        verify(exactly = 1) {
            snsTemplate.send(
                eq(topic),
                ofType<GenericMessage<String>>(),
            )
        }
    }
}
