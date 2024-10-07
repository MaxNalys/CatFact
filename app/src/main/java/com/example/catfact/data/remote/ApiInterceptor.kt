package com.example.catfact.data.remote
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add your API key and API host here
        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("X-CSRF-TOKEN", "PtnorVwZ29MdcJ6R1fRZy7h8YIwORWQgHveHHZx8")
            .build()

        return chain.proceed(requestWithHeaders)
    }
}