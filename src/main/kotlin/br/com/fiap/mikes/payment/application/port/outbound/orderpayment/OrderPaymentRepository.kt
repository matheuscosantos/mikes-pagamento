package br.com.fiap.mikes.payment.application.port.outbound.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse

interface OrderPaymentRepository {
    fun save(orderPayment: OrderPayment): OrderPaymentOutboundResponse

    fun findByOrderId(orderId: OrderId): OrderPaymentOutboundResponse?

    fun updateStatusByOrderId(
        orderId: OrderId,
        status: OrderPaymentStatus,
    )
}
