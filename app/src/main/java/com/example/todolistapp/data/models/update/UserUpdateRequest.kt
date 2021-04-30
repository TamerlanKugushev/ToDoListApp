package com.example.todolistapp.data.models.update

import com.google.gson.annotations.SerializedName

data class UserUpdateRequest(
    @SerializedName("age")
    val age: Int
)