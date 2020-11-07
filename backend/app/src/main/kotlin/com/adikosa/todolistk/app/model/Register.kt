package com.adikosa.todolistk.app.model

data class RegisterRequest(
        var firstName: String,
        var lastName: String,
        var username: String,
        var password: String
)

data class RegisterResponse(
        var firstName: String,
        var lastName: String,
        var username: String,
        var token: String?
)