package br.com.fiap.mikes.payment.application.core.domain.exception

class InvalidOrderPaymentStatusException(message: String) : InvalidValueException(TYPE, message) {
    companion object {
        private const val TYPE = "OrderPaymentStatus"
    }
}
