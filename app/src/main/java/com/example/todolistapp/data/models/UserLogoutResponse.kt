package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName
import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess

data class UserLogoutResponse(
    @SerializedName("success")
    val success: String
)