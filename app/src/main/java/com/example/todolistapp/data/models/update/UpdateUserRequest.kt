package com.example.todolistapp.data.models.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    @SerialName("age")
    val age: Int
)