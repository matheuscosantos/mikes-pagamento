package br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client

import br.com.fiap.mikes.payment.application.port.outbound.order.PaymentReceivedMessenger
import br.com.fiap.mikes.payment.application.port.outbound.order.dto.PaymentReceivedMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SnsOrderReceivedMessenger(
    private val snsMessenger: SnsMessenger,
    @Value("\${aws.topic.status-payment}")
    private val orderReceivedTopic: String,
) : PaymentReceivedMessenger {
    override fun send(paymentReceivedMessage: PaymentReceivedMessage) {
        snsMessenger.send(orderReceivedTopic, paymentReceivedMessage)
    }
}
