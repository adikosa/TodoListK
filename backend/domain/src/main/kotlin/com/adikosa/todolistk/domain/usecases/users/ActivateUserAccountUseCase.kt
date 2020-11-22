package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.UserService
import java.util.*


interface ActivateUserAccountUseCase {
    fun invoke(userId: UUID)
}

class ActivateUserAccountUseCaseImpl(
        private val userService: UserService
) : ActivateUserAccountUseCase {
    override fun invoke(userId: UUID) {
        userService.activate(userId)
    }
}
