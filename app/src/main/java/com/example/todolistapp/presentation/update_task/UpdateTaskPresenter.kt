package com.example.todolistapp.presentation.update_task

import com.example.todolistapp.utils.BaseApplication
import com.example.todolistapp.domain.TasksInteractor
import com.example.todolistapp.utils.BasePresenter
import com.example.todolistapp.utils.logException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UpdateTaskPresenter(private val id: String) : BasePresenter<UpdateTaskView>() {

    private val taskInteractor = TasksInteractor()
    private val router = BaseApplication.instance.router

    fun updateTask(description: String) {
        taskInteractor
            .updateTask(id, description)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.onTaskUpdated(id, description)
                    router.exit()
                },
                onError = {
                    logException(this, it)
                }
            ).addTo(viewCompositeDisposable)
    }
}