package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.usecases.users.DeleteUserUseCase
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
        private val registerUserUseCase: RegisterUserUseCase,
        private val loginUserUseCase: LoginUserUseCase,
        private val deleteUserUseCase: DeleteUserUseCase
) {
    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): TokenResult {
        return registerUserUseCase.invoke(registerData)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginData: LoginData): TokenResult {
        loginUserUseCase.invoke(loginData)
        TODO("07-Nov-20 finish later")
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = deleteUserUseCase.invoke(id)

}