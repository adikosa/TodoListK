package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.exceptions.EmailAlreadyTakenException
import com.adikosa.todolistk.domain.exceptions.UsernameAlreadyTakenException
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.PasswordManager
import com.adikosa.todolistk.domain.services.UserService

interface RegisterUserUseCase {
    fun invoke(registerData: RegisterData): TokenResult
}

class RegisterUserUseCaseImpl(
        private val passwordManager: PasswordManager,
        private val jwtTokenManager: JwtTokenManager,
        private val userService: UserService
) : RegisterUserUseCase {
    override fun invoke(registerData: RegisterData): TokenResult {
        val email = registerData.email
        if (userService.isEmailTaken(email)) {
            throw EmailAlreadyTakenException(email)
        }

        val username = registerData.username
        if (userService.isUsernameTaken(username)) {
            throw UsernameAlreadyTakenException(username)
        }

        registerData.password = passwordManager.encode(registerData.password)!!
        val uuid = userService.save(registerData)
        val token = jwtTokenManager.createToken(registerData.username)

        return TokenResult(uuid, token)
    }
}
