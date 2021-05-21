package com.example.todolistapp.domain

import com.example.todolistapp.data.models.delete.UserDeleteResponse
import com.example.todolistapp.data.repositories.DeleteUserRepository
import io.reactivex.Single

class DeleteUserInteractor {

    fun deleteUser(): Single<UserDeleteResponse> {
        return DeleteUserRepository
            .deleteUser()
    }
}