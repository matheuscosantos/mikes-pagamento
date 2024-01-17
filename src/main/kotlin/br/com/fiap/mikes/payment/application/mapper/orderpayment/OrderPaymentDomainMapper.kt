package br.com.fiap.mikes.payment.application.mapper.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse

interface OrderPaymentDomainMapper {
    fun new(orderPaymentOutboundResponse: OrderPaymentOutboundResponse): Result<OrderPayment>
}
