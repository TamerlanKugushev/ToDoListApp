package com.example.todolistapp.data.models.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String
)