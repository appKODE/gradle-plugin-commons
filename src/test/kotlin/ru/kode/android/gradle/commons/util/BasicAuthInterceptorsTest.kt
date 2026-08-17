package ru.kode.android.gradle.commons.util

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BasicAuthInterceptorsTest {
    private val server = MockWebServer()

    @BeforeEach
    fun start() = server.start()

    @AfterEach
    fun stop() = server.shutdown()

    @Test
    fun `buildBasicAuthInterceptor attaches Authorization and extra headers`() {
        server.enqueue(MockResponse().setResponseCode(200))
        val client =
            OkHttpClient.Builder()
                .addInterceptor(buildBasicAuthInterceptor("user", "pass", mapOf("Content-Type" to "application/json")))
                .build()

        client.newCall(Request.Builder().url(server.url("/")).build()).execute().close()

        val recorded = server.takeRequest()
        assertEquals(Credentials.basic("user", "pass"), recorded.getHeader("Authorization"))
        assertEquals("application/json", recorded.getHeader("Content-Type"))
    }
}
