package com.example.todolistapp.presentation.sign_in

import com.example.todolistapp.utils.BaseApplication
import com.example.todolistapp.Screens
import com.example.todolistapp.domain.AuthorizationInteractor
import com.example.todolistapp.utils.BasePresenter
import com.example.todolistapp.utils.logException
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SignInPresenter : BasePresenter<SignInView>() {

    private val authorizationInteractor = AuthorizationInteractor()
    private val router: Router = BaseApplication.instance.router

    fun login(email: String, password: String) {
        authorizationInteractor.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    router.newRootScreen(Screens.TasksScreen())
                },
                onError = {
                    getView()?.showError()
                    logException(this, it)
                }
            ).addTo(viewCompositeDisposable)
    }

    fun onRegisterButtonClicked() {
        router.navigateTo(Screens.RegistrationScreen())
    }
}