package com.example.todolistapp

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.models.task.TaskListResponse
import com.example.todolistapp.data.models.task.TaskResponse

object TaskConverter {

    fun toTask(taskResponse: TaskResponse): Task {
        return Task(taskResponse.task.id, taskResponse.task.description)
    }


    fun toTaskList(taskListResponse: TaskListResponse): List<Task> {
        return taskListResponse.taskResponse
    }

}