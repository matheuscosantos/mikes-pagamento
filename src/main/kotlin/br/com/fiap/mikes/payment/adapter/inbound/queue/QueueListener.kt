package br.com.fiap.mikes.payment.adapter.inbound.queue

import br.com.fiap.mikes.payment.adapter.inbound.queue.dto.QueueMessageDTO
import br.com.fiap.mikes.payment.adapter.outbound.topic.TopicClient
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class QueueListener(
    val topicClient: TopicClient
) {
    @SqsListener("\${aws.queue.payment-request}")
    fun listener(message: QueueMessageDTO) {
        println(message)
//        TODO -> ADICIONADO PARA TESTES
//        val topicMessage = TopicMessageDTO(
//            message.orderId,
//            OrderStatus.ACCEPTED
//        )
//
//        topicClient.sendMessage(topicMessage)
    }
}
