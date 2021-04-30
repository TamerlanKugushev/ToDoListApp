package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("user")
    val user: UserLogin,

    @SerializedName("token")
    val token: String
)

data class UserLogin(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String
)