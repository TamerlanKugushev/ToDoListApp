package com.example.todolistapp.data.models.update_task

import com.google.gson.annotations.SerializedName

class UpdateTaskResponse(
    @SerializedName("completed")
    var completed: Boolean,

    @SerializedName("description")
    var description: String
)

