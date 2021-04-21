package com.example.todolistapp

import com.example.todolistapp.data.interceptors.ContentTypeInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


object RetrofitHolder {

    private const val BASE_URL = "https://api-nodejs-todolist.herokuapp.com/"
    private val contentType = "application/json".toMediaType()

    val unathorizedUserService: UnauthorizedUserService by lazy {
        provideRetrofit(hasToken = false)
            .create(UnauthorizedUserService::class.java)
    }
    val athorizedUserService: AuthorizedUserService by lazy {
        provideRetrofit(hasToken = true)
            .create(AuthorizedUserService::class.java)
    }

    val jsonFormat = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        isLenient = true
    }

    private val jsonConverterFactory by lazy {
        jsonFormat.asConverterFactory(contentType)
    }

    private fun provideRetrofit(hasToken: Boolean): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(jsonConverterFactory)
            .client(provideOkHttpClient(hasToken))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun provideOkHttpClient(hasToken: Boolean): OkHttpClient {
        return provideOkHttpClientBuilder().apply {
            if (hasToken) {
                addInterceptor(ContentTypeInterceptor())
            }
        }.build()
    }

    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(ContentTypeInterceptor())
            .addInterceptor(provideHttpLoggingInterceptor())
    }

    private fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}