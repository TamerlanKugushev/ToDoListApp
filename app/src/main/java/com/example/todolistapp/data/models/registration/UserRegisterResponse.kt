package com.example.todolistapp.data.models.registration

import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(
    @SerializedName("user")
    val user: UserRegister,

    @SerializedName("token")
    val token: String
)


data class UserRegister(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("age")
    val age: Int
) {

}