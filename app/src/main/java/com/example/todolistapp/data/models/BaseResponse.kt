package com.example.todolistapp.data.models

import com.google.gson.annotations.SerializedName


data class BaseResponse<T : Any>(

    @SerializedName("data")
    val data: T? = null

)

fun <T : Any> handleResponse(response: BaseResponse<T>): T {
    return response.data?:throw Throwable("null")
}