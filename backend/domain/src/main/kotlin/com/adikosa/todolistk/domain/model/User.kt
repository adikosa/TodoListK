package com.adikosa.todolistk.domain.model

data class User(
        var id: Long?,
        var firstName: String,
        var lastName: String,
        var username: String,
        var password: String,
        var token: String?
)
