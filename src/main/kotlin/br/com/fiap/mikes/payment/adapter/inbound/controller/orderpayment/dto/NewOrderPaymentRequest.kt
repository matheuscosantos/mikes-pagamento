package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto

import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest

data class NewOrderPaymentRequest(
    val orderId: String,
) {
    fun toInbound(): NewOrderPaymentInboundRequest {
        return NewOrderPaymentInboundRequest(
            orderId,
        )
    }
}
