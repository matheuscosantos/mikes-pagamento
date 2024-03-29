package br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto

import java.time.LocalDateTime
import java.util.*

data class OrderPaymentOutboundResponse(
    val id: UUID,
    val orderId: String,
    var orderPaymentStatus: String,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)
