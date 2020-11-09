package com.adikosa.todolistk.storage

import com.adikosa.todolistk.storage.entities.PriorityEntity
import com.adikosa.todolistk.storage.entities.TodoEntity
import com.adikosa.todolistk.storage.entities.UserEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<UserEntity>
}

interface TodoRepository : JpaRepository<TodoEntity, Long> {
    fun findAllByUser_Id(userId: Long): List<TodoEntity>
}

interface PriorityRepository : JpaRepository<PriorityEntity, Long> {
    fun findByName(name: String): Optional<PriorityEntity>
}
