package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.CurrentUser
import com.adikosa.todolistk.domain.services.UserService

interface LogoutUseCase {
    fun invoke()
}

class LogoutUseCaseImpl(
        private val currentUser: CurrentUser,
        private val userService: UserService
) : LogoutUseCase {
    override fun invoke() {
        val userId = currentUser.id
        userService.removeUserToken(userId)
    }
}