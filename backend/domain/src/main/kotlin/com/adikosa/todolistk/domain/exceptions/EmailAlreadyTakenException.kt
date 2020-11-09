package com.adikosa.todolistk.domain.exceptions

class EmailAlreadyTakenException(email: String) : RuntimeException(
        "Email address $email is already taken!"
)