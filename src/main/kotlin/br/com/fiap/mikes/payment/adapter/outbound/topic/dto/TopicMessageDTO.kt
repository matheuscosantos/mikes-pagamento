package br.com.fiap.mikes.payment.adapter.outbound.topic.dto

import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus

data class TopicMessageDTO(
    val orderId: String,
    val status: OrderPaymentStatus
)
