package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.models.logout.UserLogoutResponse
import com.example.todolistapp.data.models.task.TaskRequest
import com.example.todolistapp.data.models.task.TaskResponse
import io.reactivex.Single

object Repository {
    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)
    val taskList = ArrayList<TaskResponse>()

    fun deleteUser(): Single<UserDeleteResponse> {
        return authorizedUserService
            .deleteUser(prefsHelper.getToken())
            .doOnSuccess { prefsHelper.removeToken() }
            .map { response ->
                response
            }
    }

    fun logout(): Single<UserLogoutResponse> {
        return authorizedUserService
            .logoutUser(prefsHelper.getToken())
            .doOnSuccess { prefsHelper.removeToken() }
            .map { response ->
                response
            }
    }


    fun addTask(description: String): Single<TaskResponse> {

        val taskRequest = TaskRequest(description = description)
        return authorizedUserService
            .addTask(
                prefsHelper.getToken(),
                taskRequest
            )
            .map { taskResponse ->
                taskResponse.task.description = taskRequest.description
                taskResponse
            }
    }

    fun isUserAuthorized(): Boolean {
        return prefsHelper.getToken() != null
    }


}