package br.com.fiap.mikes.payment.configuration

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SNSConfig {
    @Value("\${spring.cloud.aws.credentials.access-key}")
    private val accessKey: String? = null

    @Value("\${spring.cloud.aws.credentials.secret-key}")
    private val secretKey: String? = null

    @Value("\${spring.cloud.aws.region.static}")
    private val region: String? = null

    @Bean
    @Primary
    fun snsAsyncClient(): AmazonSNSClient {
        val credentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonSNSClientBuilder
            .standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .build() as AmazonSNSClient
    }
}
