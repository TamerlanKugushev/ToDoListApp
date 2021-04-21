package com.example.todolistapp.data.models.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("token")
    val token: String
)