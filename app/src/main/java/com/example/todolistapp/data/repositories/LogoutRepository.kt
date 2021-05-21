package com.example.todolistapp.data.repositories

import com.example.todolistapp.AppPreferencesHelper
import com.example.todolistapp.BaseApplication
import com.example.todolistapp.RetrofitHolder
import com.example.todolistapp.data.models.logout.UserLogoutResponse
import io.reactivex.Single

object LogoutRepository {

    private val authorizedUserService = RetrofitHolder.authorizedUserService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun logout(): Single<UserLogoutResponse> {
        return authorizedUserService
            .logoutUser(prefsHelper.getToken())
            .doOnSuccess { prefsHelper.removeToken() }
            .map { response ->
                response
            }
    }
}