package br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client

interface SnsMessenger {
    fun <M : Any> send(
        topic: String,
        message: M,
    )
}
