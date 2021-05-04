package com.example.todolistapp.ui.registration

import android.util.Log
import com.example.todolistapp.data.repositories.Repository
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SignInPresenter: BasePresenter<SignInView>() {

    private var counter: Int = 0

    override fun bindView(view: SignInView) {
        super.bindView(view)

        view.updateCounter(counter)
    }

    fun login(email: String, password: String) {
        Repository
            .loginUser(email.trim(), password.trim())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.navigateToTasksScreen()
                    Log.i("LOGIN", it.toString())
                },
                onError = {
                    Log.e("LOGIN", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

    fun onRegisterButtonClicked(email: String, password: String) {
        getView()?.navigateToRegistrationScreen(email.trim(), password.trim())
    }

    fun increaseCounter() {
        counter += 1
        getView()?.updateCounter(counter)
    }
}