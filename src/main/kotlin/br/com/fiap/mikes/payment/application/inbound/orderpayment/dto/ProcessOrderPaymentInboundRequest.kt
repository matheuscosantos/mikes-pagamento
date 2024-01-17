package br.com.fiap.mikes.payment.application.inbound.orderpayment.dto

data class ProcessOrderPaymentInboundRequest(
    val orderId: String,
    val paid: Boolean,
)
