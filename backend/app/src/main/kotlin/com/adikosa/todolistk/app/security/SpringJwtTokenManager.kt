package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.domain.services.JwtTokenManager
import java.util.*
import org.springframework.beans.factory.annotation.Value

class SpringJwtTokenManager(
        private val base64Encoder: Base64.Encoder
) : JwtTokenManager {

    @Value("\${security.jwt.token.secret-key}")
    private lateinit var secretKey: String

    override fun initialize() {
        secretKey = base64Encoder.encodeToString(secretKey.toByteArray())
    }

    
}