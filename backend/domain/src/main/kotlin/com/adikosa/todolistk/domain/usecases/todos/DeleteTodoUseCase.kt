package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.services.TodoService

interface DeleteTodoUseCase {
    fun invoke(todoId: String): Boolean
}

class DeleteTodoUseCaseImpl(
        private val todoService: TodoService
) : DeleteTodoUseCase {
    override fun invoke(todoId: String): Boolean {
        if (todoService.existsById(todoId)) {
            todoService.deleteById(todoId)
            return true
        }

        return false
    }

}