package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val user: User,

    @SerializedName("token")
    val token: String
)

data class User(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String
)