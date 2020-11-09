package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.TodoService

interface SaveTodoUseCase {
    fun invoke(todo: Todo): Todo
}

class SaveTodoUseCaseImpl(
        private val todoService: TodoService
) : SaveTodoUseCase {
    override fun invoke(todo: Todo): Todo {
        return todoService.save(todo)
    }

}