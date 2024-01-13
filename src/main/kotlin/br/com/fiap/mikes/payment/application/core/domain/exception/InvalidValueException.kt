package br.com.fiap.mikes.payment.application.core.domain.exception

open class InvalidValueException(val type: String, message: String) : Exception(message)
