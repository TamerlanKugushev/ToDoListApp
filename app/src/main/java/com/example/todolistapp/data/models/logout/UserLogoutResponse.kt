package com.example.todolistapp.data.models.logout

import com.google.gson.annotations.SerializedName

data class UserLogoutResponse(
    @SerializedName("success")
    val success: String
)