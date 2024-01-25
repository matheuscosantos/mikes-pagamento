# mikes-pagamento

## **Índice**

1. [Introdução](#1-introdução)
2. [Módulo `orderpayment`](#2-módulo-orderpayment)
3. [Considerações Finais](#3-considerações-finais)

## 1. Introdução
Este documento serve como uma guia de referência para o projeto Spring Boot em Kotlin relacionado a pagamentos de pedidos. O projeto é modularizado e segue as melhores práticas de desenvolvimento, utilizando o Spring Boot como framework e Kotlin como linguagem de programação.

## 2. Módulo `orderpayment`
### 2.1. Pacote `br.com.fiap.mikes.payment.adapter.inbound.controller.orderpayment`
Este pacote contém o controlador `OrderPaymentController` responsável por lidar com as requisições relacionadas a pagamentos de pedidos. Ele oferece um endpoint para processar webhooks de pagamento.

```kotlin
@RestController
@RequestMapping("/orders-payment")
class OrderPaymentController(
    private val processOrderPaymentService: ProcessOrderPaymentService,
) {
    @PostMapping("/webhook/process")
    fun process(
        @RequestBody updatedOrderPaymentInboundRequest: UpdatedOrderPaymentInboundRequest,
    ): Result<UpdatedOrderPaymentInboundRequest> {
        return processOrderPaymentService.update(updatedOrderPaymentInboundRequest)
    }
}
```

## 2.2. Pacote `br.com.fiap.mikes.payment.adapter.inbound.queue`
Este pacote contém o ouvinte de fila QueueListener, que utiliza a anotação @SqsListener para escutar mensagens da fila SQS `solicitar-pagamento`. As mensagens são processadas pelo serviço ProcessOrderPaymentService.

```kotlin
@Component
class QueueListener(
    val processOrderPaymentService: ProcessOrderPaymentService,
) {
    @SqsListener("\${aws.queue.payment-request}")
    fun listener(message: QueueMessageDTO) {
        processOrderPaymentService.create(
            NewOrderPaymentInboundRequest(message.orderId),
        )
    }
}
```

## 2.3. Pacote `br.com.fiap.mikes.payment.adapter.outbound.database`
Este pacote lida com a persistência de dados no banco de dados. A classe OrderPaymentDatabaseRepository implementa a interface OrderPaymentRepository para salvar e atualizar informações relacionadas a pagamentos de pedidos no banco de dados.

```kotlin
@Repository
class OrderPaymentDatabaseRepository(
    private val orderPaymentJpaRepository: OrderPaymentJpaRepository,
) : OrderPaymentRepository {
    // ...
}
```

## 2.4. Pacote `br.com.fiap.mikes.payment.adapter.outbound.messenger.sns.client`
Este pacote contém a implementação do serviço SnsOrderReceivedMessenger, responsável por enviar mensagens para um tópico SNS (Simple Notification Service) AWS.

```kotlin
@Service
class SnsOrderReceivedMessenger(
    private val snsMessenger: SnsMessenger,
    @Value("\${aws.topic.status-payment}")
    private val orderReceivedTopic: String,
) : PaymentReceivedMessenger {
    // ...
}
```

## 2.5. Pacote `br.com.fiap.mikes.payment.application.core.usecase.orderpayment`
Este pacote contém a implementação dos casos de uso relacionados ao processamento de pagamentos de pedidos. O principal serviço é o ProcessOrderPaymentUseCase.

```kotlin
@Service
@Transactional
class ProcessOrderPaymentUseCase(
    private val orderPaymentRepository: OrderPaymentRepository,
    private val orderPaymentDomainMapper: OrderPaymentDomainMapper,
    private val snsMessenger: SnsOrderReceivedMessenger,
) : ProcessOrderPaymentService {
    // ...
}
```

## 3. Considerações Finais

Este documento fornece uma visão geral da estrutura e funcionalidades do projeto Spring Boot em Kotlin. Certifique-se de revisar a documentação de cada classe e pacote para obter informações detalhadas sobre as implementações.

Para quaisquer dúvidas ou problemas, entre em contato com a equipe responsável pelo desenvolvimento.