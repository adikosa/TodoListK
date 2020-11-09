package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.services.PasswordManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SpringPasswordManager(
        private val strength: Int
) : PasswordManager, BCryptPasswordEncoder(strength) {

}