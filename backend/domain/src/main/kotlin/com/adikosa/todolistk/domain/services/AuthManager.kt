package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.LoginData

interface AuthManager {
    fun authenticate(loginData: LoginData)
    fun authenticate(username: String)
}
