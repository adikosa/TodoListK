package com.adikosa.todolistk.storage

import com.adikosa.todolistk.storage.entities.PriorityEntity
import com.adikosa.todolistk.storage.entities.TodoEntity
import com.adikosa.todolistk.storage.entities.UserEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<UserEntity>
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Optional<UserEntity>
}

interface TodoRepository : JpaRepository<TodoEntity, UUID> {
    fun findAllByUser_Id(uuid: UUID): List<TodoEntity>
}

interface PriorityRepository : JpaRepository<PriorityEntity, UUID> {
    fun findByName(name: String): Optional<PriorityEntity>
}
