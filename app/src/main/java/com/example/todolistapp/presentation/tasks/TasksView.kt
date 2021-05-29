package com.example.todolistapp.presentation.tasks

import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.utils.BaseView

interface TasksView : BaseView {

    fun navigateToSignInScreen()

    fun updateTaskList(tasks: List<Task>)

    fun updateState(tasksScreenStates: TasksScreenStates)

}