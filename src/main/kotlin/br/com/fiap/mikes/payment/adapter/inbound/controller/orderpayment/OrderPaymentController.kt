package br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment

import br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto.OrderPaymentDto
import br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment.dto.ProcessOrderPaymentRequest
import br.com.fiap.mikes.payment.application.port.inbound.orderpayment.ProcessOrderPaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders-payment")
class OrderPaymentController(
    private val processOrderPaymentService: ProcessOrderPaymentService,
) {
    @PostMapping("/webhook/process")
    fun process(
        @RequestBody processOrderPaymentRequest: ProcessOrderPaymentRequest,
    ): ResponseEntity<OrderPaymentDto> {
        return processOrderPaymentService.execute(processOrderPaymentRequest.toInbound())
            .map { OrderPaymentDto.from(it) }
            .map { ResponseEntity.ok(it) }
            .getOrThrow()
    }
}
