package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.UserService

interface LoginUserUseCase {
    fun invoke(loginData: LoginData): String
}

class LoginUserUseCaseImpl(
        private val userService: UserService,
        private val authManager: AuthManager
) : LoginUserUseCase {
    override fun invoke(loginData: LoginData): String {
        authManager.authenticate(loginData)

        userService.findByEmail(loginData.email)
    }

}
