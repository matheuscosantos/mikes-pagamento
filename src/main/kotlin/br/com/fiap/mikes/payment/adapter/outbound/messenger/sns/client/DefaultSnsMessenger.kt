package br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client

import io.awspring.cloud.sns.core.SnsTemplate
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service

@Service
class DefaultSnsMessenger(
    private val snsTemplate: SnsTemplate,
) : SnsMessenger {
    override fun <M : Any> send(
        topic: String,
        message: M,
    ) {
        snsTemplate.send(topic, GenericMessage(message))
    }
}
