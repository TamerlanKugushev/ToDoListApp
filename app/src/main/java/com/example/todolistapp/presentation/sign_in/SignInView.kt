package com.example.todolistapp.presentation.sign_in

import com.example.todolistapp.utils.BaseView

interface SignInView : BaseView {
    fun navigateToRegistrationScreen()

    fun navigateToTasksScreen()

    fun showProgressBar()

    fun hideProgressBar()

}