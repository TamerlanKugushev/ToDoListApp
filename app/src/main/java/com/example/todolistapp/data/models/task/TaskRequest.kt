package com.example.todolistapp.data.models.task

import com.google.gson.annotations.SerializedName

data class TaskRequest(
    @SerializedName("description")
    var description: String,
)