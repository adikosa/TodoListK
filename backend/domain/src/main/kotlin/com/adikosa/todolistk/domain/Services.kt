package com.adikosa.todolistk.domain

import com.adikosa.todolistk.domain.model.Priority
import com.adikosa.todolistk.domain.model.Todo
import com.adikosa.todolistk.domain.model.User

interface Service<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T
    fun save(t: T): T
    fun save(list: List<T>): List<T>
    fun existsById(id: ID): Boolean
    fun deleteById(id: ID)
    fun update(t: T, id: ID): T
}

interface UserService : Service<User, Long>

interface TodoService : Service<Todo, Long> {
    fun getAllByUserId(userId: Long): List<Todo>
}

interface PriorityService : Service<Priority, Long>
