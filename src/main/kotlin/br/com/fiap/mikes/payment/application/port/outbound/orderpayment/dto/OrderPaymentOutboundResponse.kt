package br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto

import java.time.LocalDateTime

data class OrderPaymentOutboundResponse(
    val id: String,
    val orderId: String,
    val orderPaymentStatus: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
