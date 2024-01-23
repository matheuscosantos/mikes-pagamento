package br.com.fiap.mikes.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ResultExtensionsTest {
    @Test
    fun `flatMap should apply transformation to success result`() {
        val result: Result<Int> = Result.success(5)
        val transformedResult = result.flatMap { Result.success(it * 2) }

        assertEquals(Result.success(10), transformedResult)
    }

    @Test
    fun `mapFailure should keep success result unchanged`() {
        val result: Result<Int> = Result.success(5)
        val transformedResult = result.mapFailure { CustomException(it.message ?: "Unknown error") }

        assertEquals(Result.success(5), transformedResult)
    }

    @Test
    fun `flatMap should propagate failure in case of failure result`() {
        val initialResult: Result<Int> = Result.failure(RuntimeException("Error"))

        val transformedResult = initialResult.flatMap { Result.success(it * 2) }

        assertTrue(transformedResult.isFailure)
    }

    @Test
    fun `flatMap should propagate failure in case of failure result during transformation`() {
        val initialResult: Result<Int> = Result.success(42)

        val transformedResult = initialResult.flatMap { Result.failure<Int>(RuntimeException("Error")) }

        assertTrue(transformedResult.isFailure)
    }

    @Test
    fun `mapFailure should transform failure in case of failure result`() {
        val initialResult: Result<Int> = Result.failure(RuntimeException("Error"))

        val transformedResult =
            initialResult.mapFailure { transformedException ->
                RuntimeException("Transformed $transformedException")
            }

        assertTrue(transformedResult.isFailure)
    }

    private class CustomException(message: String) : RuntimeException(message)
}
