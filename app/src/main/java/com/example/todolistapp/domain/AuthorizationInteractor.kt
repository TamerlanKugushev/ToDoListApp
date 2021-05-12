package com.example.todolistapp.domain

import com.example.todolistapp.data.models.UserLoginResponse
import com.example.todolistapp.data.models.registration.UserRegisterResponse
import com.example.todolistapp.data.repositories.AuthorizationRepository
import io.reactivex.Single

class AuthorizationInteractor {

    fun login(email: String, password: String): Single<UserLoginResponse> {
        return AuthorizationRepository.loginUser(
            email = email.trim(),
            password = password.trim()
        )
    }

    fun registerUser(
        name: String,
        password: String?,
        email: String?,
        age: String
    ): Single<UserRegisterResponse> {
        return AuthorizationRepository.registerUser(
            name.trim(), password ?: "", email ?: "", age.trim().toInt()
        )
    }
}