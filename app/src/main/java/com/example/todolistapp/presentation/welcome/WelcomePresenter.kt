package com.example.todolistapp.presentation.welcome

import com.example.todolistapp.BaseApplication
import com.example.todolistapp.Screens
import com.example.todolistapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router

class WelcomePresenter : BasePresenter<WelcomeView>() {

    private val router: Router = BaseApplication.instance.router

    override fun bindView(view: WelcomeView) {
        super.bindView(view)
    }

    fun navigateToSignInScreen() {
        router.navigateTo(Screens.SignInScreen())
    }
}