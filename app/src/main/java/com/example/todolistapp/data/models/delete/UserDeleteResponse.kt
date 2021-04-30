package com.example.todolistapp.data.models.delete

import com.google.gson.annotations.SerializedName

data class UserDeleteResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("age")
    val age: Int,

    @SerializedName("email")
    val email: String
)