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
//    override fun getAll(): List<User> = userRepository.findAll().toDomain()
//    override fun getById(id: Long): User = userRepository.findById(id).orElseThrow().toDomain()
//    override fun save(user: User): User = userRepository.save(user.toEntity()).toDomain()
//    override fun save(users: List<User>): List<User> = userRepository.saveAll(users.toEntity()).toDomain()
//    override fun existsById(id: Long): Boolean = userRepository.existsById(id)
//    override fun deleteById(id: Long) = userRepository.deleteById(id)
//    override fun update(user: User, id: Long): User {
//        // TODO: 07-Nov-20 finish later
//        return save(user)
//    }

    override fun save(registerData: RegisterData): String {
        val savedUser = with(registerData) {
            userRepository.save(UserEntity(firstName, lastName, email, password))
        }
        return savedUser.id.toString()
    }

    override fun isEmailTaken(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findByEmail(email: String): String {
        return userRepository.findByEmail(email).orElseThrow().id.toString()
    }
}


