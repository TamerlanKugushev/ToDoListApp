package com.example.todolistapp.presentation.tasks

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.utils.BaseView

interface TasksView : BaseView {

    fun navigateToSignInScreen()

    fun updateTaskList(tasks: List<Task>)

    fun getAllTasks(tasks: List<Task>)

    fun showProgressBar()

    fun hideProgressBar()

}