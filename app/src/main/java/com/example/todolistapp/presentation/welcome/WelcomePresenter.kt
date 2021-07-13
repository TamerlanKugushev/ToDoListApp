package com.example.todolistapp.presentation.welcome

import com.example.todolistapp.utils.BaseApplication
import com.example.todolistapp.Screens
import com.example.todolistapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router

class WelcomePresenter : BasePresenter<WelcomeView>() {

    private val router: Router = BaseApplication.instance.router

    fun navigateToSignInScreen() {
        router.navigateTo(Screens.SignInScreen())
    }
}