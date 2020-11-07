package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.TodoService
import com.adikosa.todolistk.domain.model.Todo

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