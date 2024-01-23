package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment

import br.com.fiap.mikes.payment.application.inbound.orderpayment.dto.UpdatedOrderPaymentInboundRequest
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderPaymentControllerTest {
    private lateinit var processOrderPaymentService: ProcessOrderPaymentService
    private lateinit var orderPaymentController: OrderPaymentController

    @BeforeEach
    fun setUp() {
        processOrderPaymentService = mockk(relaxed = true)
        orderPaymentController = OrderPaymentController(processOrderPaymentService)
        MockKAnnotations.init(this)
    }

    @Test
    fun `process should return updated order payment`() {
        val updatedOrderPaymentInboundRequest = UpdatedOrderPaymentInboundRequest("orderId", "PAID")
        val expectedResult = Result.success(updatedOrderPaymentInboundRequest)
        every { processOrderPaymentService.update(updatedOrderPaymentInboundRequest) } returns expectedResult

        val result: Result<UpdatedOrderPaymentInboundRequest> = orderPaymentController.process(updatedOrderPaymentInboundRequest)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `process should return ResponseEntity with OK status when successful`() {
        val updatedOrderPaymentInboundRequest = UpdatedOrderPaymentInboundRequest("orderId", "PAID")
        val expectedResult = Result.success(updatedOrderPaymentInboundRequest)
        every { processOrderPaymentService.update(updatedOrderPaymentInboundRequest) } returns expectedResult

        val responseEntity: Result<UpdatedOrderPaymentInboundRequest> =
            orderPaymentController.process(updatedOrderPaymentInboundRequest)

        assertEquals(true, responseEntity.isSuccess)
    }
}
