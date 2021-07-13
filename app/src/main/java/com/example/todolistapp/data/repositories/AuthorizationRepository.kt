package com.example.todolistapp.data.repositories

import com.example.todolistapp.utils.AppPreferencesHelper
import com.example.todolistapp.utils.BaseApplication
import com.example.todolistapp.network.RetrofitHolder
import com.example.todolistapp.data.models.UserLoginResponse
import com.example.todolistapp.data.models.login.UserLoginRequest
import com.example.todolistapp.data.models.registration.UserRegisterRequest
import com.example.todolistapp.data.models.registration.UserRegisterResponse
import io.reactivex.Single

object AuthorizationRepository {
    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun loginUser(email: String, password: String): Single<UserLoginResponse> {
        val body = UserLoginRequest(email = email, password = password)
        return unauthorizedUserService
            .loginUser(body)
            .map { response ->
                prefsHelper.setToken(response.token)
                response
            }
    }

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

    fun isUserAuthorized(): Boolean {
        return prefsHelper.getToken() != null
    }
}