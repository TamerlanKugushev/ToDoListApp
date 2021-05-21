package com.example.todolistapp.presentation.sign_in

import android.util.Log
import com.example.todolistapp.domain.AuthorizationInteractor
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SignInPresenter : BasePresenter<SignInView>() {

    private val authorizationInteractor = AuthorizationInteractor()

    fun login(email: String, password: String) {
        authorizationInteractor.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getView()?.showProgressBar() }
            .subscribeBy(
                onSuccess = {
                    getView()?.hideProgressBar()
                    getView()?.navigateToTasksScreen()
                    Log.i("LOGIN", it.toString())
                },
                onError = {
                    getView()?.hideProgressBar()
                    Log.e("LOGIN", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

    fun onRegisterButtonClicked() {
        getView()?.navigateToRegistrationScreen()
    }

}