package br.com.fiap.mikes.payment.application.inbound.orderpayment.dto

data class UpdatedOrderPaymentInboundRequest(
    val orderId: String,
    val status: String,
)
