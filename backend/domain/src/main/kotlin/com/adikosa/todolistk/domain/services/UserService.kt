package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.RegisterData

interface UserService {
    fun save(registerData: RegisterData): String
    fun isEmailTaken(email: String): Boolean
    fun findByEmail(email: String): String
}