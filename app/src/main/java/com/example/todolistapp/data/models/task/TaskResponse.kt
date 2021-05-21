package com.example.todolistapp.data.models.task

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("success")
    val success: String,

    @SerializedName("data")
    val task: Task
)

data class Task(

    var id: String = "",

    @SerializedName("description")
    var description: String
)

