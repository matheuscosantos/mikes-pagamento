package br.com.fiap.mikes.payment.application.port.inbound.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.UpdatedOrderPaymentInboundRequest

interface ProcessOrderPaymentService {
    fun create(newOrderPaymentInboundRequest: NewOrderPaymentInboundRequest): Result<OrderPayment>

    fun update(updatedOrderPaymentInboundRequest: UpdatedOrderPaymentInboundRequest): Result<UpdatedOrderPaymentInboundRequest>
}
