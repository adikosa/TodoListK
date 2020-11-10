package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.services.AuthManager
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Component

@Component
class SpringAuthManager(
        authenticationManager: AuthenticationManager
) : AuthManager {

    override fun authenticate(loginData: LoginData) {
        TODO()
    }
}