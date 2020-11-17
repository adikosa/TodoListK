package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.TodoData
import java.util.*

interface TodoService {
    fun existsById(todoId: UUID): Boolean
    fun findAllByUserId(userId: UUID): List<TodoData>
    fun save(todoData: TodoData): TodoData
    fun deleteById(todoId: UUID)
    fun update(todoData: TodoData, todoId: UUID): TodoData
    fun isUserTodoCreator(userId: UUID, todoId: UUID): Boolean
}
