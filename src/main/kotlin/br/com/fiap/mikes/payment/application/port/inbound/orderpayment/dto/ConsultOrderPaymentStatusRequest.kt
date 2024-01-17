package br.com.fiap.mikes.payment.application.port.inbound.orderpayment.dto

data class ConsultOrderPaymentStatusRequest(
    val orderId: String,
)
