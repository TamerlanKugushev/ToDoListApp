package com.example.todolistapp.presentation.registration

import com.example.todolistapp.utils.BaseView

interface RegistrationView : BaseView {

    fun navigateToTasksScreen()

    fun showProgressBar()

    fun hideProgressBar()

    fun showError()

}