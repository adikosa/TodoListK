package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.services.TodoService
import java.util.*

interface DeleteTodoUseCase {
    fun invoke(todoId: UUID): Boolean
}

class DeleteTodoUseCaseImpl(
        private val todoService: TodoService
) : DeleteTodoUseCase {
    override fun invoke(todoId: UUID): Boolean {
        if (todoService.existsById(todoId)) {
            todoService.deleteById(todoId)
            return true
        }

        return false
    }

}