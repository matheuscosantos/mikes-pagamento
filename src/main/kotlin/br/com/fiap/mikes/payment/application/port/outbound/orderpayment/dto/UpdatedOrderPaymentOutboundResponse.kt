package br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto

import java.time.LocalDateTime

class UpdatedOrderPaymentOutboundResponse(
    val id: String,
    val orderId: String,
    var orderPaymentStatus: String,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)
