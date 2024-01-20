package br.com.fiap.mikes.payment.application.core.domain.exception.order

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidValueException

class OrderPaymentAlreadyExistsException(message: String) : InvalidValueException(TYPE, message) {
    companion object {
        private const val TYPE = "OrderId"
    }
}
