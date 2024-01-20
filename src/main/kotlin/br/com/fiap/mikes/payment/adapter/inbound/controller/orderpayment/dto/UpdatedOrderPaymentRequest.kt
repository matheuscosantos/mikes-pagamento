package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto

import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import java.time.LocalDateTime

data class UpdatedOrderPaymentRequest(
    val orderId: String,
    val orderPaymentStatus: String,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun from(orderPayment: OrderPayment): UpdatedOrderPaymentRequest =
            with(orderPayment) {
                UpdatedOrderPaymentRequest(
                    orderId.value,
                    orderPaymentStatus.value,
                )
            }
    }
}
