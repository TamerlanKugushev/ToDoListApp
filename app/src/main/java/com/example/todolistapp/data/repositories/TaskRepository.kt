package com.example.todolistapp.data.repositories

import com.example.todolistapp.network.RetrofitHolder
import com.example.todolistapp.TaskConverter
import com.example.todolistapp.data.models.handleResponse
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.update_task.UpdateTaskResponse
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

    fun updateTask(id: String, description: String): Single<UpdateTaskResponse> {
        return authorizedUserService
            .updateTask(id, description)
            .map(::handleResponse)

    }
}