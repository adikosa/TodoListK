package com.adikosa.todolistk.storage

import com.adikosa.todolistk.domain.model.Priority
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {

}

interface TodoRepository : JpaRepository<TodoEntity, Long> {
    fun findAllByUser_Id(userId: Long): List<TodoEntity>
}

interface PriorityRepository : JpaRepository<PriorityEntity, Long> {
    fun findByName(name: String): Optional<PriorityEntity>
}
