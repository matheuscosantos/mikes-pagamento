package br.com.fiap.mikes.payment.application.core.domain.exception.orderpayment

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidValueException

class InvalidOrderPaymentIdException(message: String) : InvalidValueException(TYPE, message) {
    companion object {
        private const val TYPE = "OrderPaymentId"
    }
}
