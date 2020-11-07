package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.model.User

interface GetUserUseCase {
    fun invoke(id: Long): User
}

class GetUserUseCaseImpl(
        private val userService: UserService
) : GetUserUseCase {
    override fun invoke(id: Long): User {
        return userService.getById(id)
    }
}
