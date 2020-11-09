package com.adikosa.todolistk.domain.services

interface PasswordManager {
    fun encode(rawPassword: CharSequence?): String?
    fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean
}
