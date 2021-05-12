package com.example.todolistapp.presentation.sign_in

import com.example.todolistapp.utils.BaseView

interface SignInView: BaseView {

    fun navigateToRegistrationScreen(email: String, password: String)

    fun navigateToTasksScreen()

    fun updateCounter(counter: Int)
}