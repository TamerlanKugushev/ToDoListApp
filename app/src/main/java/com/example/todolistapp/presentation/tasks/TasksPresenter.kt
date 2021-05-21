package com.example.todolistapp.presentation.tasks

import android.util.Log
import com.example.todolistapp.domain.AuthorizationInteractor
import com.example.todolistapp.domain.DeleteUserInteractor
import com.example.todolistapp.domain.LogoutInteractor
import com.example.todolistapp.presentation.sign_in.SignInView
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TasksPresenter : BasePresenter<TasksView>() {

    private val logoutInteractor = LogoutInteractor()
    private val deleteInteractor = DeleteUserInteractor()


    fun logout() {
        logoutInteractor
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.navigateToSignInScreen()
                    Log.i("LOGOUT", it.toString())
                },
                onError = {
                    Log.e("LOGOUT", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

    fun deleteUser() {
        deleteInteractor
            .deleteUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.navigateToSignInScreen()
                    Log.i("DEL", it.toString())
                },
                onError = {
                    Log.e("DEL", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

}