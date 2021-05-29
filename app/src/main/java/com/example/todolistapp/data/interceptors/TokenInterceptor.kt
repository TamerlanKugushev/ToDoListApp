package com.example.todolistapp.data.interceptors

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    private val prefsHelp = AppPreferencesHelper(BaseApplication.instance.baseContext)

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefsHelp.getToken()
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}