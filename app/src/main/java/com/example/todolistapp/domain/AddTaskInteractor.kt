package com.example.todolistapp.domain

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.repositories.TaskRepository
import io.reactivex.Single

class AddTaskInteractor {
    fun addTask(description: String): Single<Task> {
        return TaskRepository.addTask(description.trim())
    }
}