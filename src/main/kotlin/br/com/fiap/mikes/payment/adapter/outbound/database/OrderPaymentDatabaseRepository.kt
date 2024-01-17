package br.com.fiap.mikes.payment.adapter.outbound.database

import br.com.fiap.mikes.payment.adapter.outbound.database.entity.OrderPaymentEntity
import br.com.fiap.mikes.payment.adapter.outbound.database.jpa.OrderPaymentJpaRepository
import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.OrderPaymentRepository
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class OrderPaymentDatabaseRepository(
    private val orderPaymentJpaRepository: OrderPaymentJpaRepository,
) : OrderPaymentRepository {
    override fun save(orderPayment: OrderPayment): OrderPaymentOutboundResponse {
        return orderPaymentJpaRepository
            .save(OrderPaymentEntity.from(orderPayment))
            .toOutbound()
    }

    override fun findByOrderId(orderId: OrderId): OrderPaymentOutboundResponse? {
        return orderPaymentJpaRepository.findByOrderId(orderId.value)
            .map { it.toOutbound() }
            .getOrNull()
    }
}
