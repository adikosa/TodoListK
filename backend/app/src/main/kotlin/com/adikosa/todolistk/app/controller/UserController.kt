package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.model.ApiMessage
import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.model.TokenResult
import com.adikosa.todolistk.domain.usecases.users.ActivateUserAccountUseCase
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCase
import com.adikosa.todolistk.domain.usecases.users.LogoutUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
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
        private val logoutUseCase: LogoutUseCase,
        private val activateUserAccountUseCase: ActivateUserAccountUseCase
) {
    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): ResponseEntity<TokenResult> {
        return ok(registerUserUseCase.invoke(registerData))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginData: LoginData): ResponseEntity<TokenResult> {
        return ok(loginUserUseCase.invoke(loginData))
    }

    @IsAuthenticated
    @PostMapping("/logout")
    fun login(): ResponseEntity<ApiMessage> {
        logoutUseCase.invoke()
        return ok(ApiMessage("User logged out"))
    }

    @GetMapping("/activate")
    fun activateUser(@RequestParam(name = "token") userActivationToken: String): ResponseEntity<ApiMessage> {
        activateUserAccountUseCase.invoke(UUID.fromString(userActivationToken))
        return ok(ApiMessage("User account activated"))
    }

}
