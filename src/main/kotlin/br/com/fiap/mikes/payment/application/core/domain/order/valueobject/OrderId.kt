package br.com.fiap.mikes.payment.application.core.domain.order.valueobject

import br.com.fiap.mikes.payment.application.core.domain.exception.order.InvalidOrderIdException
import java.util.UUID

@JvmInline
value class OrderId private constructor(val value: String) {
    companion object {
        fun new(value: String): Result<OrderId> {
            val uuid =
                runCatching { UUID.fromString(value) }
                    .getOrElse { return Result.failure(InvalidOrderIdException("invalid order id.")) }

            return Result.success(OrderId(uuid.toString()))
        }

        fun generate() = OrderId(UUID.randomUUID().toString())
    }
}
