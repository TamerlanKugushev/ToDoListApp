package com.example.todolistapp.presentation.tasks

import android.util.Log
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.domain.DeleteUserInteractor
import com.example.todolistapp.domain.LogoutInteractor
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TasksPresenter : BasePresenter<TasksView>() {

    private val logoutInteractor = LogoutInteractor()
    private val deleteInteractor = DeleteUserInteractor()

    private val taskList = mutableListOf<Task>()

    override fun bindView(view: TasksView) {
        super.bindView(view)

        getView()?.updateTaskList(taskList)
    }

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

    fun onTaskAdded(task: Task) {
        taskList.add(task)
        getView()?.updateTaskList(taskList)
    }

}