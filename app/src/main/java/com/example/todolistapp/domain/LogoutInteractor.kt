package com.example.todolistapp.domain

import com.example.todolistapp.data.repositories.LogoutRepository
import io.reactivex.Completable

class LogoutInteractor {

    fun logout(): Completable {
        return LogoutRepository.logout()
    }
}