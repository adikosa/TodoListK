package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.model.RegisterRequest
import com.adikosa.todolistk.domain.model.RegisterResponse

interface RegisterUserUseCase {
    fun invoke(registerRequest: RegisterRequest): RegisterResponse
}

class RegisterUserUseCaseImpl(
        private val userService: UserService
) : RegisterUserUseCase {
    override fun invoke(registerRequest: RegisterRequest): RegisterResponse {
        return userService.register(registerRequest)
    }
}
