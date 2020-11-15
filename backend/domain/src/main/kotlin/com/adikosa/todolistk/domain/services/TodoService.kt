package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.TodoData

interface TodoService {
    fun existsById(todoId: String): Boolean
    fun findAllByUserId(userId: String): List<TodoData>
    fun save(todoData: TodoData): TodoData
    fun deleteById(todoId: String)
    fun update(todoData: TodoData, todoId: String): TodoData
    fun isUserTodoCreator(userId: String, todoId: String): Boolean
}
