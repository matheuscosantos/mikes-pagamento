package br.com.fiap.mikes.payment.adapter.outbound.topic

import br.com.fiap.mikes.payment.adapter.outbound.topic.dto.TopicMessageDTO
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import org.springframework.stereotype.Component

@Component
class TopicClient(private val amazonSNS: AmazonSNS) {
    fun sendMessage(messageDTO: TopicMessageDTO) {
        val request = PublishRequest(
            "arn:aws:sns:us-east-2:644237782704:status-pagamento",
            messageDTO.toString(),
            "teste",
        )
        amazonSNS.publish(request)
    }
}
