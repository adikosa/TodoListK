package com.adikosa.todolistk.storage

import com.adikosa.todolistk.storage.entities.PriorityEntity
import com.adikosa.todolistk.storage.entities.RoleEntity
import com.adikosa.todolistk.storage.entities.TodoEntity
import com.adikosa.todolistk.storage.entities.UserEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): UserEntity?
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): UserEntity?
    fun existsByUsernameAndToken(username: String, token: String): Boolean
}

interface RoleRepository : JpaRepository<RoleEntity, UUID> {
    fun findByName(s: String): RoleEntity?
}

interface TodoRepository : JpaRepository<TodoEntity, UUID> {
    fun findAllByUser_Id(userId: UUID): List<TodoEntity>
    fun existsByIdAndUser_Id(todoId: UUID, userId: UUID): Boolean
}

interface PriorityRepository : JpaRepository<PriorityEntity, UUID> {
    fun findByName(name: String): PriorityEntity?
}
