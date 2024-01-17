package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto

import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.ProcessOrderPaymentInboundRequest

data class ProcessOrderPaymentRequest(
    val orderId: String,
    val paid: Boolean,
) {
    fun toInbound(): ProcessOrderPaymentInboundRequest {
        return ProcessOrderPaymentInboundRequest(
            orderId,
            paid,
        )
    }
}
