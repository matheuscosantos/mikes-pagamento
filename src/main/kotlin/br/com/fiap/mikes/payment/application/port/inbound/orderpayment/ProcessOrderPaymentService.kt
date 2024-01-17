package br.com.fiap.mikes.payment.application.port.inbound.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.ProcessOrderPaymentInboundRequest

fun interface ProcessOrderPaymentService {
    fun execute(processOrderPaymentInboundRequest: ProcessOrderPaymentInboundRequest): Result<OrderPayment>
}
