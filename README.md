# Mikes - Pagamento

Esta documentação descreve a estrutura e o funcionamento da aplicação de pagamentos, destacando as principais classes e suas responsabilidades.

[Desenho da arquitetura](https://drive.google.com/file/d/12gofNmXk8W2QnhxiFWCI4OmvVH6Vsgun/view?usp=drive_link)

## 1. Controlador de Pagamentos de Pedido (OrderPaymentController)

### Descrição

O controlador OrderPaymentController é responsável por receber requisições HTTP para processamento de pagamentos de pedidos.

## 2. Ouvinte de Fila (QueueListener)

### Descrição

O ouvinte de fila QueueListener é responsável por escutar mensagens em uma fila SQS da AWS e chamar o serviço processOrderPaymentService para criar um novo pedido de pagamento.

## 3. Cliente do SNS para Mensagens de Pedido Recebido (SnsOrderReceivedMessenger)

### Descrição

O cliente do SNS SnsOrderReceivedMessenger é responsável por enviar mensagens para um tópico SNS da AWS, notificando sobre o recebimento de um pagamento de pedido.

## 4. Adicionar as variáveis de ambiente

AWS_ACCESS_KEY_ID = Adicione o access key da sua conta AWS
AWS_SECRET_ACCESS_KEY = Adicione o secret access da sua conta AWS
SQS_URL = Adicionar endereço da fila solicitar-pagamento na AWS

## 5. Executando

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


## 6. Padrão SAGA Coreografado

O padrão Saga Coreografado foi utilizado no projeto pois é usado em aplicações distribuídas e microserviços para garantir a consistência em transações que envolvem múltiplos serviços. Nesse padrão, cada serviço envolvido em uma transação realiza uma parte da operação e emite eventos para indicar seu estado. Outros serviços ou um coordenador monitoram esses eventos e coordenam as operações para garantir que a transação seja concluída com sucesso ou revertida de forma consistente.

Existem várias razões para usar o padrão Saga Coreografado em aplicações como as descritas nos repositórios do projeto "Mikes":

Consistência distribuída: Como as transações envolvem vários serviços, é importante garantir que eles estejam em um estado consistente, mesmo em caso de falhas.
Escalabilidade e desempenho: O padrão permite que as operações sejam distribuídas entre vários serviços, melhorando a escalabilidade e o desempenho do sistema.
Resiliência: O padrão ajuda a tornar o sistema mais resiliente a falhas, pois permite que as transações sejam revertidas de forma consistente em caso de falha em um dos serviços.
Visibilidade e monitoramento: Como cada serviço emite eventos para indicar seu estado, é mais fácil monitorar e diagnosticar problemas no sistema.
Flexibilidade e manutenção: O padrão torna o sistema mais flexível, pois permite que novos serviços sejam adicionados ou alterados sem alterar a lógica de negócios existente.
Em resumo, o padrão Saga Coreografado é usado em aplicações distribuídas e microserviços para garantir a consistência e a integridade das transações, mesmo em um ambiente distribuído e com alta escalabilidade.
