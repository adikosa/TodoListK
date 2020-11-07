package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.TodoService

interface DeleteTodoUseCase {
    fun invoke(todoId: Long): Boolean
}

class DeleteTodoUseCaseImpl(
        private val todoService: TodoService
) : DeleteTodoUseCase {
    override fun invoke(todoId: Long): Boolean {
        if (todoService.existsById(todoId)) {
            todoService.deleteById(todoId)
            return true
        }

        return false
    }

}