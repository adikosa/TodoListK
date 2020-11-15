package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.RegisterData

interface UserService {
    fun findIdByUsername(username: String): String
    fun findIdByEmail(email: String): String
    fun isUsernameTaken(username: String): Boolean
    fun isEmailTaken(email: String): Boolean
    fun save(registerData: RegisterData): String
}