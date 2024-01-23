package br.com.fiap.mikes.payment.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JsonUtilTest {
    @Test
    fun `serializeJson should serialize object to JSON string`() {
        val person = Person("John Doe", 30)

        val jsonString = person.serializeJson()

        val expectedJson = """{"name":"John Doe","age":30}"""
        assertEquals(expectedJson, jsonString)
    }
}

data class Person(val name: String, val age: Int)
