package com.example.todolistapp

import com.example.todolistapp.data.models.*
import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.models.logout.UserLogoutResponse
import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.data.models.update.UserUpdateRequest
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizedUserService {

    @GET("user/me")
    fun getLoggedInUserViaToken(@Header("Authorization") token: String): Single<UserLogin>

    @POST("user/logout")
    fun logoutUser(@Header("Authorization") token: String?): Single<UserLogoutResponse>

    @DELETE("user/me")
    fun deleteUser(@Header("Authorization") token: String?): Single<UserDeleteResponse>


    @PUT("user/me")
    fun updateUser(
        @Header("Authorization") token: String,
        @Body body: UserUpdateRequest
    ): Single<UserUpdateResponse>


    @POST("task")
    fun addTask(@Header("Authorization") token: String?): Single<TaskResponse>


}

