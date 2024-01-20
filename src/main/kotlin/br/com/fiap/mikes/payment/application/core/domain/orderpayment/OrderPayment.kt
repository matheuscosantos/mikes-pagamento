package br.com.fiap.mikes.payment.application.core.domain.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import java.time.LocalDateTime
import java.util.*

class OrderPayment(
    val id: UUID,
    val orderId: OrderId,
    val orderPaymentStatus: OrderPaymentStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun new(
            orderId: OrderId,
            orderPaymentStatus: OrderPaymentStatus,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
        ): Result<OrderPayment> {
            return Result.success(OrderPayment(UUID.randomUUID(), orderId, orderPaymentStatus, createdAt, updatedAt))
        }
    }

    fun payment(paid: Boolean): OrderPayment {
        val updatedAt = LocalDateTime.now()

        val status =
            when (paid) {
                true -> OrderPaymentStatus.ACCEPTED
                else -> OrderPaymentStatus.REFUSED
            }

        return OrderPayment(
            UUID.randomUUID(),
            orderId,
            status,
            createdAt,
            updatedAt,
        )
    }
}
