package com.example.todolistapp.data.models.login

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)