package com.example.todolistapp.domain

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.models.update_task.UpdateTaskResponse
import com.example.todolistapp.data.repositories.TaskRepository
import io.reactivex.Single

class TasksInteractor {

    fun getAllTasks(): Single<List<Task>> {
        return TaskRepository.getAllTasks()
    }

    fun updateTask(id: String, description: String): Single<UpdateTaskResponse> {
        return TaskRepository.updateTask(id, description)
    }
}