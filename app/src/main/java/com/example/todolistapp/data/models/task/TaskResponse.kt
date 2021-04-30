package com.example.todolistapp.data.models.task

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("description")
    var description: String,
)