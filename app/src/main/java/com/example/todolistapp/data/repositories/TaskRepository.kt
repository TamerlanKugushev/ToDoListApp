package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.TaskConverter
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import io.reactivex.Completable
import io.reactivex.Single

object TaskRepository {

    private val authorizedUserService = RetrofitHolder.authorizedUserService

    fun addTask(description: String): Single<Task> {
        val taskRequest = TaskRequest(description = description)
        return authorizedUserService
            .addTask(taskRequest)
            .map { it.task }
    }

    fun getAllTasks(): Single<List<Task>> {
        return authorizedUserService
            .getAllTask()
            .map(TaskConverter::toTaskList)
    }
}