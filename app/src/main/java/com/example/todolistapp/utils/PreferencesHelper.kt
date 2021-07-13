package com.example.todolistapp.utils

interface PreferencesHelper {

    fun getToken(): String?

    fun setToken(accessToken: String)

    fun removeToken()

}