package com.example.todolistapp.presentation.add_task

import android.util.Log
import com.example.todolistapp.domain.AddTaskInteractor
import com.example.todolistapp.utils.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AddTaskPresenter : BasePresenter<AddTaskView>() {

    private val addTaskInteractor = AddTaskInteractor()

    fun addTask(description: String) {
        addTaskInteractor
            .addTask(description = description)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.addTask(it)
                    Log.i("ADD", it.toString())
                },
                onError = {
                    Log.i("ADD", it.toString())
                }
            ).addTo(viewCompositeDisposable)
    }
}



