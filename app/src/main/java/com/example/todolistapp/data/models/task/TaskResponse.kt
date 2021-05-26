package com.example.todolistapp.data.models.task

import com.google.gson.annotations.SerializedName

data class TaskResponse(

    @SerializedName("data")
    val task: Task

)

data class Task(

    @SerializedName("_id")
    var id: String,

    @SerializedName("description")
    var description: String,
)

