package cucumber.orderpayment

import br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.OrderPaymentController
import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.UpdatedOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals

class OrderPaymentControllerCucumberTest {
    private val processOrderPaymentService = mockk<ProcessOrderPaymentService>()
    private val orderPaymentController = OrderPaymentController(processOrderPaymentService)
    private var result: Result<UpdatedOrderPaymentInboundRequest>? = null

    @Given("the Order Payment is available")
    fun givenTheOrderPaymentServiceIsAvailable() {
        // Setup any necessary mocking or setup here
    }

    @When("the order payment webhook is triggered with orderId {string} and status {string}")
    fun whenTheOrderPaymentWebhookIsTriggeredWithOrderIdAndStatus(
        orderId: String,
        status: String,
    ) {
        every { processOrderPaymentService.update(any()) } returns
            Result.success(
                UpdatedOrderPaymentInboundRequest(orderId, status),
            )
        result = orderPaymentController.process(UpdatedOrderPaymentInboundRequest(orderId, status))
    }

    @Then("should be processed successfully")
    fun thenTheOrderPaymentShouldBeProcessedSuccessfully() {
        assertEquals(Result.success(UpdatedOrderPaymentInboundRequest("e7f3a7ab-cb6f-480c-b77f-eefca352a942", "ACCEPTED")), result)
    }
}
