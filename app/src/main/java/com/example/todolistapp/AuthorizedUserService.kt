package com.example.todolistapp

import com.example.todolistapp.data.models.login.UserRequest
import com.example.todolistapp.data.models.login.UserResponse
import com.example.todolistapp.data.models.update.UpdateUserRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthorizedUserService {

    @POST("user/login")
    fun loginUser(@Body body: UserRequest): Single<UserResponse>

    @POST("user/login")
    fun updateUser(@Body body: UpdateUserRequest): Completable

    @GET("user/me")
    fun getLoggedInUserViaToken(@Header("Authorization") token: String): Single<UserResponse>

    @POST("user/logout")
    fun logoutUser(@Header("Authorization") token: String): Completable

    @GET("user/me")
    fun deleteUser(@Header("Authorization") token: String): Single<UserResponse>
}

