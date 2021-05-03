package com.example.todolistapp.data.models.task

import android.app.ActivityManager
import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("success")
    val success: String,

    @SerializedName("data")
    val task: Task
) {
}

data class Task(
    @SerializedName("description")
    var description: String,
)

