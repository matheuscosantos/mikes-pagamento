package br.com.fiap.mikes.payment.application.port.outbound.order

import br.com.fiap.mikes.payment.application.port.outbound.order.dto.PaymentReceivedMessage

interface PaymentReceivedMessenger {
    fun send(paymentReceivedMessage: PaymentReceivedMessage)
}
