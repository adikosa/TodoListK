package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.RegisterData
import java.util.*

interface UserService {
    fun save(registerData: RegisterData): UUID
    fun isEmailTaken(email: String): Boolean
    fun findByEmail(email: String)
}