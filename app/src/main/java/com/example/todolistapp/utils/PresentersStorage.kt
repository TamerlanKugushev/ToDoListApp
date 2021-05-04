package com.example.todolistapp.utils

object PresentersStorage {

    private val presenters = hashMapOf<String, BasePresenter<*>>()

    fun getPresenter(viewId: String): BasePresenter<*>? {
        return presenters[viewId]
    }

    fun <T: BaseView> savePresenter(id: String, presenter: BasePresenter<T>) {
        presenters[id] = presenter
    }

    fun removePresenter(id: String) {
        presenters.remove(id)
    }
}