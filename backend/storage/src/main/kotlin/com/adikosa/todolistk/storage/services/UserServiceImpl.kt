package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.UserEntity
import java.lang.RuntimeException
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

    override fun findIdByUsername(username: String): String {
        val user = userRepository.findByUsername(username)?: throw RuntimeException()
        return user.id.toString()
    }

    override fun findIdByEmail(email: String): String {
        val user = userRepository.findByEmail(email)?: throw RuntimeException()
        return user.id.toString()
    }

    override fun isUsernameTaken(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}


