package com.example.todolistapp.domain

import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.data.repositories.TaskRepository
import io.reactivex.Single

class AddTaskInteractor {
    fun addTask(description: String): Single<TaskResponse> {
        return TaskRepository.addTask(description.trim())
    }
}