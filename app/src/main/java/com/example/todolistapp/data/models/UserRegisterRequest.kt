package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserRegisterRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("age")
    val age: Int
)