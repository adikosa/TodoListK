package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.usecases.users.ActivateUserAccountUseCase
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import java.util.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api")
class UserController(
        private val registerUserUseCase: RegisterUserUseCase,
        private val loginUserUseCase: LoginUserUseCase,
        private val activateUserAccountUseCase: ActivateUserAccountUseCase
) {
    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): TokenResult {
        return registerUserUseCase.invoke(registerData)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginData: LoginData): TokenResult {
        return loginUserUseCase.invoke(loginData)
    }

    @IsAuthenticated
    @PostMapping("/logout")
    fun login(): TokenResult {
        TODO()
    }

    @GetMapping("/activate")
    fun activateUser(@RequestParam(name = "token") userActivationToken: String) {
        activateUserAccountUseCase.invoke(UUID.fromString(userActivationToken))
    }

}
