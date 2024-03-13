# mikes-pagamento

## **Índice**

1. [Introdução](#1-introdução)
2. [Módulo `orderpayment`](#2-módulo-orderpayment)
3. [Considerações Finais](#3-considerações-finais)

## 1. Introdução
Este documento serve como uma guia de referência para o projeto Spring Boot em Kotlin relacionado a pagamentos de pedidos. O projeto é modularizado e segue as melhores práticas de desenvolvimento, utilizando o Spring Boot como framework e Kotlin como linguagem de programação.

[Desenho da arquitetura](https://drive.google.com/file/d/12gofNmXk8W2QnhxiFWCI4OmvVH6Vsgun/view?usp=drive_link)

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

## 3. Executando

### Pré-requisitos
- Java JDK 17 instalado
- Docker instalado e em execução

### Construa o projeto Spring Boot:

```bash
./gradlew build
```

### Crie a imagem Docker:

```bash
docker build -t payment-service .
```

### Execute o contêiner Docker:
```bash
docker run -p 8050:8050 payment-service
```

Agora, o serviço deve estar em execução localmente na porta 8050.

### Observações

Certifique-se de que as variáveis de ambiente e configurações necessárias (como credenciais de banco de dados, configurações do AWS, etc.) estão corretamente configuradas no seu ambiente local ou no Dockerfile.
Para configurar o ambiente localmente, você precisará instalar e configurar o AWS CLI com suas credenciais.
Este é um exemplo básico e pode ser necessário ajustar dependendo das suas necessidades e ambiente de desenvolvimento.


## 4 - Padrão SAGA Coreografado

O padrão Saga Coreografado foi utilizado no projeto pois é usado em aplicações distribuídas e microserviços para garantir a consistência em transações que envolvem múltiplos serviços. Nesse padrão, cada serviço envolvido em uma transação realiza uma parte da operação e emite eventos para indicar seu estado. Outros serviços ou um coordenador monitoram esses eventos e coordenam as operações para garantir que a transação seja concluída com sucesso ou revertida de forma consistente.

Existem várias razões para usar o padrão Saga Coreografado em aplicações como as descritas nos repositórios do projeto "Mikes":

Consistência distribuída: Como as transações envolvem vários serviços, é importante garantir que eles estejam em um estado consistente, mesmo em caso de falhas.
Escalabilidade e desempenho: O padrão permite que as operações sejam distribuídas entre vários serviços, melhorando a escalabilidade e o desempenho do sistema.
Resiliência: O padrão ajuda a tornar o sistema mais resiliente a falhas, pois permite que as transações sejam revertidas de forma consistente em caso de falha em um dos serviços.
Visibilidade e monitoramento: Como cada serviço emite eventos para indicar seu estado, é mais fácil monitorar e diagnosticar problemas no sistema.
Flexibilidade e manutenção: O padrão torna o sistema mais flexível, pois permite que novos serviços sejam adicionados ou alterados sem alterar a lógica de negócios existente.
Em resumo, o padrão Saga Coreografado é usado em aplicações distribuídas e microserviços para garantir a consistência e a integridade das transações, mesmo em um ambiente distribuído e com alta escalabilidade.