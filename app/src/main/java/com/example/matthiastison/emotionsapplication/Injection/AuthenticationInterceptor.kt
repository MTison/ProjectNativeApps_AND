package com.example.matthiastison.emotionsapplication.Injection

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor : Interceptor {
    private var defaultHeaders: Map<String, String>? = null

    init {
        this.defaultHeaders = HashMap()
    }

    fun setDefaultHeaders(headers: Map<String, String>) {
        this.defaultHeaders = headers
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        for (key in this.defaultHeaders!!.keys) {
            requestBuilder.addHeader(key, this.defaultHeaders!![key])
        }

        return chain.proceed(requestBuilder.build())
    }
}