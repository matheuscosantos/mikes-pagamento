package br.com.fiap.mikes.payment

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sns")
class SnsController (private val amazonSNS: AmazonSNS) {

    @GetMapping("/publish")
    fun sendMessage(){
        val request = PublishRequest(
            "arn:aws:sns:us-east-2:644237782704:status-pagamento",
            "teste mensagem 123456",
            "teste"
        )
        amazonSNS.publish(request)
    }

}