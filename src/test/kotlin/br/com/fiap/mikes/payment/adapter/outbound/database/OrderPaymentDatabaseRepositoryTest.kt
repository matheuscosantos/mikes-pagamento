package br.com.fiap.mikes.payment.adapter.outbound.database

import br.com.fiap.mikes.payment.adapter.outbound.database.entity.OrderPaymentEntity
import br.com.fiap.mikes.payment.adapter.outbound.database.jpa.OrderPaymentJpaRepository
import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class OrderPaymentDatabaseRepositoryTest {
    private lateinit var orderPaymentJpaRepository: OrderPaymentJpaRepository
    private lateinit var orderPaymentDatabaseRepository: OrderPaymentDatabaseRepository

    @BeforeEach
    fun setUp() {
        orderPaymentJpaRepository = mockk()
        orderPaymentDatabaseRepository = OrderPaymentDatabaseRepository(orderPaymentJpaRepository)
        MockKAnnotations.init(this)
    }

    @Test
    fun `save should call save method of OrderPaymentJpaRepository and return OrderPaymentOutboundResponse`() {
        val orderId = OrderId.generate()
        val orderPaymentStatus = OrderPaymentStatus.ACCEPTED
        val orderPayment = OrderPayment.new(orderId, orderPaymentStatus, LocalDateTime.now(), LocalDateTime.now())
        val orderPaymentEntity = OrderPaymentEntity.from(orderPayment.getOrThrow())
        val savedEntity = orderPaymentEntity.copy(id = UUID.randomUUID())

        every { orderPaymentJpaRepository.save(match { it.orderId == orderPaymentEntity.orderId }) } returns savedEntity

        val result = orderPaymentDatabaseRepository.save(orderPayment.getOrThrow())

        assertEquals(savedEntity.toOutbound(), result)

        verify(exactly = 1) { orderPaymentJpaRepository.save(match { it.orderId == orderPaymentEntity.orderId }) }

        confirmVerified(orderPaymentJpaRepository)
    }

    @Test
    fun `findByOrderId should return OrderPaymentOutboundResponse if entity is found`() {
        val orderId = OrderId.generate()
        val orderPaymentEntity =
            OrderPaymentEntity(
                id = UUID.randomUUID(),
                orderId = orderId.value,
                status = OrderPaymentStatus.ACCEPTED.value,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )

        every { orderPaymentJpaRepository.findByOrderId(orderId.value) } returns Optional.of(orderPaymentEntity)

        val result = orderPaymentDatabaseRepository.findByOrderId(orderId)

        assertEquals(orderPaymentEntity.toOutbound(), result)
        verify(exactly = 1) { orderPaymentJpaRepository.findByOrderId(orderId.value) }
        confirmVerified(orderPaymentJpaRepository)
    }

    @Test
    fun `findByOrderId should return null if entity is not found`() {
        val orderId = OrderId.generate()
        every { orderPaymentJpaRepository.findByOrderId(orderId.value) } returns Optional.empty()

        val result = orderPaymentDatabaseRepository.findByOrderId(orderId)
        assertNull(result)
        verify(exactly = 1) { orderPaymentJpaRepository.findByOrderId(orderId.value) }

        confirmVerified(orderPaymentJpaRepository)
    }
}
