package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.model.*
import com.adikosa.todolistk.domain.usecases.users.DeleteUserUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
        private val deleteUserUseCase: DeleteUserUseCase,
        private val registerUserUseCase: RegisterUserUseCase
) {
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): RegisterResponse {
        val user = registerUserUseCase.invoke(
                registerRequest.firstName,
                registerRequest.lastName,
                registerRequest.firstName,
                registerRequest.password
        )

        return RegisterResponse(user.firstName, user.lastName, user.username, user.token)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        // TODO: 07-Nov-20 finish later
        return LoginResponse("none")
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = deleteUserUseCase.invoke(id)

}