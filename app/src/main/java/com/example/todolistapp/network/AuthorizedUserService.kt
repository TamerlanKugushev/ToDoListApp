package com.example.todolistapp.network

import com.example.todolistapp.data.models.BaseResponse
import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.models.task.TaskListResponse
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.data.models.update_task.UpdateTaskResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizedUserService {

    @POST("user/logout")
    fun logoutUser(): Completable

    @DELETE("user/me")
    fun deleteUser(): Single<UserDeleteResponse>

    @POST("task")
    fun addTask(@Body body: TaskRequest): Single<TaskResponse>

    @GET("task")
    fun getAllTask(): Single<TaskListResponse>

    @PUT("task/{id}")
    fun updateTask(
        @Path("id") id: String,
        @Query("description") description:String,
        @Query("completed") completed: Boolean = true
    ): Single<BaseResponse<UpdateTaskResponse>>
}

