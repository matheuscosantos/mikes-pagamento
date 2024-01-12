package br.com.fiap.mikes.payment

import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class QueueListener{

    @Value("\${spring.cloud.aws.sqs.uri}")
    private val endpoint: String? = null


    @SqsListener("\${aws.queue.payment-request}")
    fun listener(message: String) {
        println(message)
    }

}