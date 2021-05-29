package com.example.todolistapp.presentation.tasks

import android.util.Log
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.domain.DeleteUserInteractor
import com.example.todolistapp.domain.LogoutInteractor
import com.example.todolistapp.domain.TasksInteractor
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TasksPresenter : BasePresenter<TasksView>() {

    private val logoutInteractor = LogoutInteractor()
    private val deleteInteractor = DeleteUserInteractor()
    private val tasksInteractor = TasksInteractor()
    private var taskList = mutableListOf<Task>()
    private var tasksScreenStates: TasksScreenStates = TasksScreenStates.START

    init {
        loadAllTasks()
    }

    override fun bindView(view: TasksView) {
        super.bindView(view)
        getView()?.updateTaskList(taskList)
        getView()?.updateState(tasksScreenStates)
    }


    fun logout() {
        logoutInteractor
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    getView()?.navigateToSignInScreen()
                },
                onError = {
                    Log.e("LOGOUT", it.toString())
                }
            ).addTo(dataCompositeDisposable)
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
            ).addTo(dataCompositeDisposable)
    }

    fun onTaskAdded(task: Task) {
        taskList.add(task)
        getView()?.updateTaskList(taskList)
    }

    private fun loadAllTasks() {
        tasksInteractor.getAllTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                tasksScreenStates = TasksScreenStates.LOADING
                getView()?.updateState(tasksScreenStates)
            }
            .subscribeBy(
                onSuccess = {
                    taskList = it.toMutableList()
                    tasksScreenStates = TasksScreenStates.CONTENT
                    getView()?.updateState(tasksScreenStates)
                    getView()?.updateTaskList(taskList)
                    Log.i("TASKS", it.toString())
                },
                onError = {
                    Log.e("TASKS", it.toString())
                }
            ).addTo(dataCompositeDisposable)
    }

}