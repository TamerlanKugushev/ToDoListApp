package com.example.todolistapp

interface PreferencesHelper {

    fun getToken(): String?

    fun setToken(accessToken: String?)

}