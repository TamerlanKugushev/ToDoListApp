package com.example.todolistapp.data.interceptors

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.MainActivity
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Bearer","")
                .build()
        )
    }
}