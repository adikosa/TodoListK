package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.storage.RoleRepository
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.UserEntity
import java.lang.RuntimeException
import java.util.*
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository
) : UserService {
    override fun register(registerData: RegisterData): UUID {
        val savedUser = with(registerData) {
            val user = UserEntity(firstName, lastName, username, email, password, isEnabled = false)
            val userRole = roleRepository.findByName("ROLE_USER")?: throw RuntimeException()
            user.roles = user.roles.plus(userRole)
            userRepository.save(user)
        }
        return savedUser.id!!
    }

    override fun activate(userId: UUID) {
        val user = findById(userId).apply { isEnabled = true }
        userRepository.save(user)
    }

    override fun saveUserToken(userId: UUID, token: String) {
        val user = findById(userId).apply { this.token = token }
        userRepository.save(user)
    }

    override fun removeUserToken(userId: UUID) {
        val user = findById(userId).apply { this.token = null }
        userRepository.save(user)
    }

    private fun findById(userId: UUID): UserEntity {
        return userRepository.findById(userId).orElseThrow { RuntimeException() }
    }

    override fun hasUserToken(username: String, token: String): Boolean {
        return userRepository.existsByUsernameAndToken(username, token)
    }

    override fun isEmailTaken(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findIdByUsername(username: String): UUID {
        val user = userRepository.findByUsername(username)?: throw RuntimeException()
        return user.id!!
    }

    override fun findIdByEmail(email: String): UUID {
        val user = userRepository.findByEmail(email)?: throw RuntimeException()
        return user.id!!
    }

    override fun isUsernameTaken(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}
