package com.adikosa.todolistk.domain.services

interface JwtTokenManager {
    fun createToken(uuid: String): String
}