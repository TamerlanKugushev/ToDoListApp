package com.example.todolistapp

import com.example.todolistapp.data.interceptors.ContentTypeInterceptor
import com.example.todolistapp.data.interceptors.TokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHolder {

    private const val BASE_URL = "https://api-nodejs-todolist.herokuapp.com/"

    var authorizedUserService: AuthorizedUserService =
        provideRetrofit(hasToken = true).create(AuthorizedUserService::class.java)
    var unauthorizedUserService: UnauthorizedUserService =
        provideRetrofit(hasToken = false).create(UnauthorizedUserService::class.java)

    private fun provideRetrofit(hasToken: Boolean): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(hasToken))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun provideOkHttpClient(hasToken: Boolean): OkHttpClient {
        return provideOkHttpClientBuilder().apply {
            if (hasToken) {
                addInterceptor(TokenInterceptor())
            } else {
                addInterceptor(ContentTypeInterceptor())
            }
        }.build()
    }

    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
    }

    private fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}