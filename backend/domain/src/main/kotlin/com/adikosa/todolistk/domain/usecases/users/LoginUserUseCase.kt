package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.UserService

interface LoginUserUseCase {
    fun invoke(loginData: LoginData): TokenResult
}

class LoginUserUseCaseImpl(
        private val jwtTokenManager: JwtTokenManager,
        private val userService: UserService,
        private val authManager: AuthManager
) : LoginUserUseCase {
    override fun invoke(loginData: LoginData): TokenResult {
        authManager.authenticate(loginData)

        val uuid = userService.findByUsername(loginData.username)
        val token = jwtTokenManager.createToken(uuid)
        return TokenResult(uuid, token)
    }

}
