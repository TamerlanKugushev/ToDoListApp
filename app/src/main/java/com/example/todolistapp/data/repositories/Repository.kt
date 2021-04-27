package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.registration.UserRegisterRequest
import com.example.todolistapp.data.models.UserResponse
import com.example.todolistapp.data.models.registration.UserRegisterResponse
import io.reactivex.Single

object Repository {
    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun registerUser(name: String, password: String, email: String?): Single<UserRegisterResponse> {
        val body = UserRegisterRequest(
            name = name,
            password = password,
            email = "",
            age = 25
        )
        return unauthorizedUserService
            .registerUser(body)
            .map { response ->
                prefsHelper.setToken(response.token)
                response
            }
    }


}