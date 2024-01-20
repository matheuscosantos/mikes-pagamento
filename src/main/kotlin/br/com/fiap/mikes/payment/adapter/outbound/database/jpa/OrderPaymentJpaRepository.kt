package br.com.fiap.mikes.payment.adapter.outbound.database.jpa

import br.com.fiap.mikes.payment.adapter.outbound.database.entity.OrderPaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderPaymentJpaRepository : JpaRepository<OrderPaymentEntity, String> {
    fun findByOrderId(orderId: String): Optional<OrderPaymentEntity>

    @Modifying
    @Query("UPDATE pagamento_pedido SET status = :status, atualizado_em = CURRENT_TIMESTAMP WHERE id_pedido = :orderId", nativeQuery = true)
    fun updateStatusByOrderId(
        @Param("orderId") orderId: String,
        @Param("status") status: String,
    ): Int
}
