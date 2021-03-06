package com.example.todolistapp.presentation.tasks

import com.example.todolistapp.utils.BaseApplication
import com.example.todolistapp.Screens
import com.example.todolistapp.data.models.task.Task
import com.example.todolistapp.domain.DeleteUserInteractor
import com.example.todolistapp.domain.LogoutInteractor
import com.example.todolistapp.domain.TasksInteractor
import com.example.todolistapp.utils.BasePresenter
import com.example.todolistapp.utils.logException
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TasksPresenter : BasePresenter<TasksView>() {

    private val logoutInteractor = LogoutInteractor()
    private val deleteInteractor = DeleteUserInteractor()
    private val tasksInteractor = TasksInteractor()
    private val taskListSubject = BehaviorRelay.create<List<Task>>()
    private val router = BaseApplication.instance.router
    private var tasksScreenStates = TasksScreenStates.START

    init {
        loadAllTasks()
    }

    override fun bindView(view: TasksView) {
        super.bindView(view)
        subscribeTaskList()
    }

    fun logout() {
        logoutInteractor
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    router.navigateTo(Screens.SignInScreen())
                },
                onError = {
                    logException(this, it)
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
                    router.navigateTo(Screens.SignInScreen())
                },
                onError = {
                    logException(this, it)
                }
            ).addTo(dataCompositeDisposable)
    }

    fun onTaskAdded(task: Task) {
        val taskList = taskListSubject.value.orEmpty().toMutableList()
        taskList.add(task)
        taskListSubject.accept(taskList)
    }

    fun navigateToUpdateScreen(id:String) {
        router.navigateTo(Screens.UpdateScreen(id))
    }

    fun onTaskUpdatedById(id: String, description: String) {
        val taskList = taskListSubject.value.orEmpty().toMutableList()
        val taskForUpdate = taskList.firstOrNull { it.id == id } ?: return
        taskForUpdate.description = description
        taskListSubject.accept(taskList)
    }

    private fun loadAllTasks() {
        tasksInteractor.getAllTasks()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    taskListSubject.accept(it)
                },
                onError = {
                    logException(this, it)
                }
            ).addTo(dataCompositeDisposable)
    }

    private fun subscribeTaskList() {
        taskListSubject.hide()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                tasksScreenStates = TasksScreenStates.LOADING
                getView()?.updateState(tasksScreenStates)
            }
            .subscribeBy(
                onNext = {
                    tasksScreenStates = TasksScreenStates.CONTENT
                    getView()?.updateState(tasksScreenStates)
                    getView()?.updateTaskList(it)
                },
                onError = {
                    logException(this, it)
                }
            ).addTo(viewCompositeDisposable)
    }

}