package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.UserEntity
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun save(registerData: RegisterData): String {
        val savedUser = with(registerData) {
            userRepository.save(UserEntity(firstName, lastName, username, email, password))
        }
        return savedUser.id.toString()
    }

    override fun isEmailTaken(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findByUsername(username: String): String {
        return userRepository.findByUsername(username).orElseThrow().id.toString()
    }

    override fun findByEmail(email: String): String {
        return userRepository.findByEmail(email).orElseThrow().id.toString()
    }

    override fun isUsernameTaken(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}


