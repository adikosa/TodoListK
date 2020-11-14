package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.TodoData

interface TodoService {
    fun existsById(uuid: String): Boolean
    fun getAllByUserId(userUUID: String): List<TodoData>
    fun save(todo: TodoData): TodoData
}