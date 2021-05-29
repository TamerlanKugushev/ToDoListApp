package com.example.todolistapp.presentation.sign_in

import android.util.Log
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.Screens
import com.example.todolistapp.domain.AuthorizationInteractor
import com.example.todolistapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SignInPresenter : BasePresenter<SignInView>() {

    private val router: Router = BaseApplication.instance.router

    private val authorizationInteractor = AuthorizationInteractor()

    fun login(email: String, password: String) {
        authorizationInteractor.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getView()?.showProgressBar() }
            .subscribeBy(
                onSuccess = {
                    getView()?.hideProgressBar()
                    router.navigateTo(Screens.RegistrationScreen())
                    Log.i("LOGIN", it.toString())
                },
                onError = {
                    getView()?.hideProgressBar()
                    Log.e("LOGIN", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

    fun onRegisterButtonClicked() {
        router.navigateTo(Screens.RegistrationScreen())
    }

}