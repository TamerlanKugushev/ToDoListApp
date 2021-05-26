package com.example.todolistapp.data.models.task

import com.google.gson.annotations.SerializedName

data class TaskListResponse(
    @SerializedName("data")
    val taskResponse: List<Task>
)

