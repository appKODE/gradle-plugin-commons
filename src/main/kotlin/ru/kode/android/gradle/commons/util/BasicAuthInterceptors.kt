package ru.kode.android.gradle.commons.util

import okhttp3.Credentials
import okhttp3.Interceptor

/**
 * Attaches an HTTP Basic Auth `Authorization` header (and any [extraHeaders]) to every request.
 */
fun buildBasicAuthInterceptor(
    username: String,
    password: String,
    extraHeaders: Map<String, String> = emptyMap(),
): Interceptor =
    Interceptor { chain ->
        val requestBuilder =
            chain.request().newBuilder()
                .header("Authorization", Credentials.basic(username, password))
        extraHeaders.forEach { (name, value) -> requestBuilder.header(name, value) }
        chain.proceed(requestBuilder.build())
    }
