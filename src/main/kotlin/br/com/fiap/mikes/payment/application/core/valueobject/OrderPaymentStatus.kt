package br.com.fiap.mikes.payment.application.core.valueobject

import br.com.fiap.mikes.payment.application.core.domain.exception.InvalidOrderPaymentStatusException

enum class OrderPaymentStatus(val value: String) {
    ACCEPTED("ACCEPTED"),
    REFUSED("REFUSED"),
    WAITING("WAITING"),
    ;

    companion object {
        fun findByValue(value: String): Result<OrderPaymentStatus> {
            val status =
                entries.firstOrNull { it.value == value }
                    ?: return Result.failure(
                        InvalidOrderPaymentStatusException("Invalid Order Payment Status: '$value'."),
                    )
            return Result.success(status)
        }
    }
}
