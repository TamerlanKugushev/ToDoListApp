package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.UserRegisterRequest
import com.example.todolistapp.data.models.UserResponse
import io.reactivex.Single

object Repository {

    private val unauthorizedUserService = RetrofitHolder.unauthorizedUserService
    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun registerUser(email: String, password: String): Single<UserResponse> {
        val body = UserRegisterRequest(
            name = "Unknown",
            email = email,
            password = password,
            age = 25
        )
        return unauthorizedUserService.registerUser(body)
            .map { response ->
                prefsHelper.setToken(response.token)
                response
            }
    }

}