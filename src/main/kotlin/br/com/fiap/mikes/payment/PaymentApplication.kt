package br.com.fiap.mikes.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentApplication

@Suppress("ktlint:standard:indent")
fun main(args: Array<String>) {
	runApplication<PaymentApplication>(*args)
}
