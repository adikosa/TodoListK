package com.adikosa.todolistk.domain.services

interface JwtTokenManager {
    fun createToken(username: String): String
    fun validate(token: String)
    fun getUsername(token: String): String
}
