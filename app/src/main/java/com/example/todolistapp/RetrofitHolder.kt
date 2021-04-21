package com.example.todolistapp

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHolder {

    private const val BASE_URL="https://api-nodejs-todolist.herokuapp.com/"
    private var retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    var userService: UserService = retrofit.create(UserService::class.java)
}