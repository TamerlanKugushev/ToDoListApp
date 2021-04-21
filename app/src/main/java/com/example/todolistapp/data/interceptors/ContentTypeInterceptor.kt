package com.example.todolistapp.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}