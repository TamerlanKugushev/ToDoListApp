package com.example.todolistapp.presentation.tasks

import com.example.todolistapp.data.models.task.TaskResponse
import com.example.todolistapp.utils.BaseView

interface TasksView : BaseView {
    fun navigateToSignInScreen()
}