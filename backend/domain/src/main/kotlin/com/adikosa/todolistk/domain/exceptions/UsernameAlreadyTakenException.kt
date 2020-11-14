package com.adikosa.todolistk.domain.exceptions

class UsernameAlreadyTakenException(username: String) : RuntimeException(
        "Username $username is already taken!"
)