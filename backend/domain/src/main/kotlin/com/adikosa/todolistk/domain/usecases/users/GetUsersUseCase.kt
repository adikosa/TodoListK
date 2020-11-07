package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.model.User

interface GetUsersUseCase {
    fun invoke(): List<User>
}

class GetUsersUseCaseImpl(
        private val userService: UserService
) : GetUsersUseCase {
    override fun invoke(): List<User> {
        TODO("Not yet implemented")
    }
}