package br.com.fiap.mikes.payment.configuration

import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient


@Configuration
class SQSConfig {
    @Value("\${spring.cloud.aws.credentials.access-key}")
    private val accessKey: String? = null

    @Value("\${spring.cloud.aws.credentials.secret-key}")
    private val secretKey: String? = null

    @Value("\${spring.cloud.aws.region.static}")
    private val region: String? = null

    @Bean
    fun sqsAsyncClient(): SqsAsyncClient {
        return SqsAsyncClient
            .builder()
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider
                    .create(AwsBasicCredentials.create(accessKey, secretKey))
            )
            .build()
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient?): SqsTemplate {
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient!!).build()
    }
}