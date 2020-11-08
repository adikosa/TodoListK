package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.model.RegisterRequest
import com.adikosa.todolistk.domain.model.RegisterResponse
import com.adikosa.todolistk.domain.model.User
import com.adikosa.todolistk.storage.UserEntity
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.toDomain
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun getAll(): List<User> = userRepository.findAll().toDomain()
    override fun getById(id: Long): User = userRepository.findById(id).orElseThrow().toDomain()
    override fun save(user: User): User = userRepository.save(user.toEntity()).toDomain()
    override fun save(users: List<User>): List<User> = userRepository.saveAll(users.toEntity()).toDomain()
    override fun existsById(id: Long): Boolean = userRepository.existsById(id)
    override fun deleteById(id: Long) = userRepository.deleteById(id)
    override fun update(user: User, id: Long): User {
        // TODO: 07-Nov-20 finish later
        return save(user)
    }

    override fun register(registerRequest: RegisterRequest): RegisterResponse {
        val savedUser = with(registerRequest) {
            userRepository.save(UserEntity(firstName, lastName, username, password))
        }

        return RegisterResponse(savedUser.id, "token")
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
                firstName, lastName, username, password, token
        )
    }

    private fun List<User>.toEntity(): List<UserEntity> {
        return map { it.toEntity() }
    }
}


