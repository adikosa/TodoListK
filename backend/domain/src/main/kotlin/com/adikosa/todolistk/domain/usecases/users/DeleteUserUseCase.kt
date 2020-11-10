package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.UserService


interface DeleteUserUseCase {
    fun invoke(id: Long): Boolean
}

class DeleteUserUseCaseImpl(
        private val userService: UserService
): DeleteUserUseCase {
    override fun invoke(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}
