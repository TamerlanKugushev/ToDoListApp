package com.example.todolistapp

import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.models.logout.UserLogoutResponse
import com.example.todolistapp.data.models.task.TaskListResponse
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorizedUserService {

    @POST("user/logout")
    fun logoutUser(): Completable

    @DELETE("user/me")
    fun deleteUser(): Single<UserDeleteResponse>

    @POST("task")
    fun addTask(@Body body: TaskRequest): Single<TaskResponse>

    @GET("task")
    fun getAllTask(): Single<TaskListResponse>
}

