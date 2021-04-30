package com.example.todolistapp

import com.example.todolistapp.data.models.*
import com.example.todolistapp.data.models.login.UserLoginRequest
import com.example.todolistapp.data.models.registration.UserRegisterRequest
import com.example.todolistapp.data.models.registration.UserRegisterResponse
import io.reactivex.Single
import retrofit2.http.*

interface UnauthorizedUserService {

    @POST("user/register")
    fun registerUser(@Body body: UserRegisterRequest?): Single<UserRegisterResponse>

    @POST("user/login")
    fun loginUser(@Body body: UserLoginRequest): Single<UserLoginResponse>

    /*@GET("user/me")
    fun getLoggedInUserViaToken(@Header("Authorization") token: String): Single<User>

    @POST("user/logout")
    fun logoutUser(@Header("Authorization") token: String): Single<UserLogoutResponse>

    @DELETE("user/me")
    fun deleteUser(@Header("Authorization") token: String): Single<User>


    @PUT("user/me")
    fun updateUser(
        @Header("Authorization") token: String,
        @Body body: UserUpdateRequest
    ): Single<UserUpdateResponse>*/

}