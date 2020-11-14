package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.services.PasswordManager
import org.springframework.security.crypto.password.PasswordEncoder

class SpringPasswordManager(
        private val passwordEncoder: PasswordEncoder
) : PasswordManager {
    override fun encode(rawPassword: CharSequence?): String? {
        return passwordEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }

}