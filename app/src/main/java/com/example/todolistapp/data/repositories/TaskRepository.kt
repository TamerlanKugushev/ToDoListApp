package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import io.reactivex.Single

object TaskRepository {
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun addTask(description: String): Single<TaskResponse> {

        val taskRequest = TaskRequest(description = description)
        return authorizedUserService
            .addTask(
                prefsHelper.getToken(),
                taskRequest
            )
            .map { taskResponse ->
                taskResponse.task.description = taskRequest.description
                taskResponse
            }
    }
}