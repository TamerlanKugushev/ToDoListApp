package com.example.todolistapp.presentation.update_task

import com.example.todolistapp.utils.BaseView

interface UpdateTaskView : BaseView {

    fun onTaskUpdated(id: String, description: String)
}