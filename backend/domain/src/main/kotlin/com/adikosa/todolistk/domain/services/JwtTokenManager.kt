package com.adikosa.todolistk.domain.services

interface JwtTokenManager {
    fun createToken(username: String): String
    fun isValid(token: String): Boolean
    fun getUsername(token: String): String
}
