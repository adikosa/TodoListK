package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.RegisterData
import java.util.*

interface UserService {
    fun findIdByUsername(username: String): UUID
    fun findIdByEmail(email: String): UUID
    fun isUsernameTaken(username: String): Boolean
    fun isEmailTaken(email: String): Boolean
    fun register(registerData: RegisterData): UUID
    fun activate(userId: UUID)
}
