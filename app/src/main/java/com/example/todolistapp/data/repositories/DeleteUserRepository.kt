package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.delete.UserDeleteResponse
import io.reactivex.Single

object DeleteUserRepository {

    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun deleteUser(): Single<UserDeleteResponse> {
        return authorizedUserService
            .deleteUser()
            .doOnSuccess { prefsHelper.removeToken() }
            .map { response ->
                response
            }
    }
}