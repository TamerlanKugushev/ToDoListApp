package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserUpdateResponse(
    @SerializedName("data")
    val userUpdate: UserUpdate,

    @SerializedName("success")
    val success: String
)

data class UserUpdate(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("age")
    val age: Int
)