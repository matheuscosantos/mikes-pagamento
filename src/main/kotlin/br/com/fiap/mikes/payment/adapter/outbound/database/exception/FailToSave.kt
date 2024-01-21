package br.com.fiap.mikes.payment.adapter.outbound.database.exception

import br.com.fiap.mikes.payment.application.core.usecase.exception.NotFoundException

class FailToSave(message: String) : NotFoundException(TYPE, message) {
    companion object {
        private const val TYPE = "Order"
    }
}
