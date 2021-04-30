package com.example.todolistapp.data.repositories

import android.app.ActivityManager
import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.login.UserLoginRequest
import com.example.todolistapp.data.models.UserLoginResponse
import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.models.logout.UserLogoutResponse
import com.example.todolistapp.data.models.registration.UserRegisterRequest
import com.example.todolistapp.data.models.registration.UserRegisterResponse
import com.example.todolistapp.data.models.task.TaskResponse
import io.reactivex.Single

object Repository {
    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)
    val taskList = ArrayList<TaskResponse>()

    fun registerUser(
        name: String,
        password: String,
        email: String,
        age: Int
    ): Single<UserRegisterResponse> {
        val body = UserRegisterRequest(
            name = name,
            password = password,
            email = email,
            age = age
        )
        return unauthorizedUserService
            .registerUser(body)
            .map { response ->
                prefsHelper.setToken(response.token)
                response
            }
    }


    fun loginUser(
        email: String,
        password: String
    ): Single<UserLoginResponse> {
        val body = UserLoginRequest(email = email, password = password)
        return unauthorizedUserService
            .loginUser(body)
            .map { response ->
                prefsHelper.setToken(response.token)
                response
            }
    }

    fun deleteUser(): Single<UserDeleteResponse> {
        return authorizedUserService
            .deleteUser(prefsHelper.getToken())
            .map { response ->
                response
            }
    }

    fun logout(): Single<UserLogoutResponse> {
        return authorizedUserService
            .logoutUser(prefsHelper.getToken())
            .map { response ->
                response
            }
    }

    fun addTask(description: String): Single<TaskResponse> {

        return authorizedUserService
            .addTask(prefsHelper.getToken())
            .map { taskResponse ->
                taskResponse.description = description
                taskResponse
            }
    }


}