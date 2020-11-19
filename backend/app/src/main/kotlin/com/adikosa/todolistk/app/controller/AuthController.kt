package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api")
class AuthController(
        private val registerUserUseCase: RegisterUserUseCase,
        private val loginUserUseCase: LoginUserUseCase
) {
    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): TokenResult {
        return registerUserUseCase.invoke(registerData)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginData: LoginData): TokenResult {
        return loginUserUseCase.invoke(loginData)
    }
}
