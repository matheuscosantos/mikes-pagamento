package br.com.fiap.mikes.payment.adapter.outbound.database.entity

import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.port.outbound.orderpayment.dto.OrderPaymentOutboundResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "pagamento_pedido")
data class OrderPaymentEntity(
    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID,
    @Column(name = "id_pedido", length = 36)
    val orderId: String,
    @Column(name = "status", length = 50)
    var status: String,
    @Column(name = "criado_em")
    val createdAt: LocalDateTime,
    @Column(name = "atualizado_em")
    var updatedAt: LocalDateTime,
) {
    companion object {
        fun from(orderPayment: OrderPayment): OrderPaymentEntity =
            with(orderPayment) {
                return OrderPaymentEntity(
                    UUID.randomUUID(),
                    orderId.value,
                    orderPaymentStatus.value,
                    createdAt,
                    updatedAt,
                )
            }
    }

    fun toOutbound(): OrderPaymentOutboundResponse {
        return OrderPaymentOutboundResponse(
            id,
            orderId,
            status,
            createdAt,
            updatedAt,
        )
    }

    fun updateStatus(newStatus: String) {
        this.status = newStatus
        this.updatedAt = LocalDateTime.now()
    }
}
