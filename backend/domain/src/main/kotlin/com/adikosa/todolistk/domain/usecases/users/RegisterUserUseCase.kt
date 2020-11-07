package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.model.User

interface RegisterUserUseCase {
    fun invoke(firstName: String, lastName: String, username: String, password: String): User
}

class RegisterUserUseCaseImpl(
        private val userService: UserService
) : RegisterUserUseCase {
    override fun invoke(firstName: String, lastName: String, username: String, password: String): User {
        // TODO: 07-Nov-20 modify later 
        return userService.save(User(0L, firstName, lastName, username, password, null))
    }
}
