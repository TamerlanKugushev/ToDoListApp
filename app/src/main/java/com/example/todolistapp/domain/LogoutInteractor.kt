package com.example.todolistapp.domain

import com.example.todolistapp.data.models.logout.UserLogoutResponse
import com.example.todolistapp.data.repositories.LogoutRepository
import io.reactivex.Completable
import io.reactivex.Single

class LogoutInteractor {

    fun logout(): Completable {
        return LogoutRepository.logout()
    }
}