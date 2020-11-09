package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.exceptions.EmailAlreadyTakenException
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.services.PasswordManager
import com.adikosa.todolistk.domain.services.UserService

interface RegisterUserUseCase {
    fun invoke(registerData: RegisterData): TokenResult
}

class RegisterUserUseCaseImpl(
        private val passwordManager: PasswordManager,
        private val userService: UserService
) : RegisterUserUseCase {
    override fun invoke(registerData: RegisterData): TokenResult {
        val email = registerData.email
        if (userService.isEmailTaken(email)) {
            throw EmailAlreadyTakenException(email)
        }

        registerData.password = passwordManager.encode(registerData.password)!!
        val userUUID = userService.save(registerData)
        val token = "createToken"

        return TokenResult(userUUID, token)
    }
}
