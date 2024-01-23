package br.com.fiap.mikes.payment.adapter.inbound.queue

import br.com.fiap.mikes.payment.adapter.inbound.queue.dto.QueueMessageDTO
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QueueListenerTest {
    private lateinit var processOrderPaymentService: ProcessOrderPaymentService
    private lateinit var queueListener: QueueListener

    @BeforeEach
    fun setUp() {
        processOrderPaymentService = mockk(relaxed = true)
        queueListener = QueueListener(processOrderPaymentService)
    }

    @Test
    fun `listener should call create method of ProcessOrderPaymentService with correct parameters`() {
        val orderId = "12345"
        val message = QueueMessageDTO(orderId)

        queueListener.listener(message)

        verify(exactly = 1) {
            processOrderPaymentService.create(
                match { it.orderId == orderId },
            )
        }
    }
}
