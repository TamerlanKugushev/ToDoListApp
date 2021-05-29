package com.example.todolistapp.presentation.add_task

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.utils.BaseView

interface AddTaskView : BaseView {
    fun addTask(task: Task)
}