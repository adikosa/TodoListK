package com.adikosa.todolistk.domain.model

data class RegisterRequest(
        var firstName: String,
        var lastName: String,
        var username: String,
        var password: String
)

data class RegisterResponse(
        var id: Long?,
        var token: String?
)