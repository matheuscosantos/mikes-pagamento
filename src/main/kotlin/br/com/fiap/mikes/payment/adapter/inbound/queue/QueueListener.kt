package br.com.fiap.mikes.payment.adapter.inbound.queue

import br.com.fiap.mikes.payment.adapter.inbound.queue.dto.QueueMessageDTO
import br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client.DefaultSnsMessenger
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class QueueListener(
    val topicClient: DefaultSnsMessenger,
    val processOrderPaymentService: ProcessOrderPaymentService,
) {
    @SqsListener("\${aws.queue.payment-request}")
    fun listener(message: QueueMessageDTO) {
        processOrderPaymentService.create(
            NewOrderPaymentInboundRequest(message.orderId),
        )
    }
}
