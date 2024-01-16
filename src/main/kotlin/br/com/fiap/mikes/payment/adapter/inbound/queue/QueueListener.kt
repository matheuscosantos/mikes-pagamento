package br.com.fiap.mikes.payment.adapter.inbound.queue

import br.com.fiap.mikes.payment.adapter.inbound.queue.dto.QueueMessageDTO
import br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client.DefaultSnsMessenger
import io.awspring.cloud.sqs.annotation.SqsListener
import java.util.logging.Logger

class QueueListener(
    val topicClient: DefaultSnsMessenger,
) {
    @SqsListener("\${aws.queue.payment-request}")
    fun listener(message: QueueMessageDTO) {
        Logger
            .getLogger("Listener: $message")
            .info(message.orderId)
    }
}
