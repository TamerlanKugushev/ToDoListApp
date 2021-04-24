package com.example.todolistapp

import com.example.todolistapp.data.models.*
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizedUserService {


    /*@POST("user/register")
    fun registerUser(@Body body: UserRegisterRequest): Single<UserResponse>

    @POST("user/login")
    fun loginUser(@Body body: UserRequest): Single<UserResponse>*/

    @GET("user/me")
    fun getLoggedInUserViaToken(@Header("Authorization") token: String): Single<User>

    @POST("user/logout")
    fun logoutUser(@Header("Authorization") token: String): Single<UserLogoutResponse>

    @DELETE("user/me")
    fun deleteUser(@Header("Authorization") token: String): Single<User>


    /*@PUT("user/me")
    fun updateUser(
        @Header("Authorization") token: String,
        @Body body: UserUpdateRequest
    ): Single<UserUpdateResponse>*/


}

