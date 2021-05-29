package com.example.todolistapp.presentation.registration

import android.util.Log
import com.example.todolistapp.domain.AuthorizationInteractor
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RegistrationPresenter : BasePresenter<RegistrationView>() {

    private val authorizationInteractor = AuthorizationInteractor()

    fun registerUser(
        name: String,
        password: String,
        email: String,
        age: String
    ) {
        authorizationInteractor
            .registerUser(name, password, email, age)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { getView()?.showProgressBar() }
            .subscribeBy(
                onSuccess = {
                    getView()?.hideProgressBar()
                    getView()?.navigateToTasksScreen()
                    Log.i("REG", it.toString())
                }, onError = {
                    getView()?.hideProgressBar()
                    getView()?.showError()
                    Log.e("REG", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

}