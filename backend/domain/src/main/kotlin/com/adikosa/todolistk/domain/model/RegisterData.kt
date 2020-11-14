package com.adikosa.todolistk.domain.model

data class RegisterData(
        var firstName: String,
        var lastName: String,
        var username: String,
        var email: String,
        var password: String
)