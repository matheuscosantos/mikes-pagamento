package cucumber.orderpayment

import br.com.fiap.mikes.payment.adapter.inbound.queue.QueueListener
import br.com.fiap.mikes.payment.adapter.inbound.queue.dto.QueueMessageDTO
import br.com.fiap.mikes.payment.application.core.domain.order.valueobject.OrderId
import br.com.fiap.mikes.payment.application.core.domain.orderpayment.OrderPayment
import br.com.fiap.mikes.payment.application.core.valueobject.OrderPaymentStatus
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.NewOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime
import java.util.*

class QueueListenerStepsTest {
    private val processOrderPaymentService = mockk<ProcessOrderPaymentService>()
    private val queueListener = QueueListener(processOrderPaymentService)
    private var result: Result<NewOrderPaymentInboundRequest>? = null

    @Given("the Order Payment Service is available")
    fun givenTheOrderPaymentServiceIsAvailable() {
    }

    @When("a message is received on the SQS queue with orderId {string}")
    fun whenAMessageIsReceivedOnTheSQSQueueWithOrderId(orderId: String) {
        every {
            processOrderPaymentService.create(
                match { it.orderId == orderId },
            )
        } returns
            Result.success(
                OrderPayment(
                    UUID.randomUUID(),
                    OrderId.new("e7f3a7ab-cb6f-480c-b77f-eefca352a942").getOrThrow(),
                    OrderPaymentStatus.ACCEPTED,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                ),
            )

        queueListener.listener(QueueMessageDTO(orderId))
    }

    @Then("the order payment should be processed successfully")
    fun thenTheOrderPaymentShouldBeProcessedSuccessfully() {
        verify(exactly = 1) {
            processOrderPaymentService.create(match { it.orderId == "e7f3a7ab-cb6f-480c-b77f-eefca352a942" })
        }
        val captSlot = slot<NewOrderPaymentInboundRequest>()
        verify { processOrderPaymentService.create(capture(captSlot)) }

        val capturedRequest = captSlot.captured

        // Verifica se o resultado é o esperado
        assertEquals("e7f3a7ab-cb6f-480c-b77f-eefca352a942", capturedRequest.orderId)
    }
}
