package com.example.todolistapp

import com.example.todolistapp.data.models.User
import com.example.todolistapp.data.models.UserRequest
import com.example.todolistapp.data.models.UserResponse
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun loginUser(@Body body: UserRequest): Single<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("user/me")
    fun getLoggedInUserViaToken(@Header("Authorization") token: String): Single<User>

    @Headers("Content-Type: application/json")
    @POST("user/logout")
    fun logoutUser(@Header("Authorization") token: String): Single<Boolean>

    @Headers("Content-Type: application/json")
    @GET("user/me")
    fun deleteUser(@Header("Authorization") token: String): Single<User>


}

