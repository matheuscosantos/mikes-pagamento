package br.com.fiap.mikes.payment.application.port.outbound.order.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentReceivedMessage(
    @JsonProperty("orderId")
    val orderId: String,
    @JsonProperty("status")
    val status: String,
)
