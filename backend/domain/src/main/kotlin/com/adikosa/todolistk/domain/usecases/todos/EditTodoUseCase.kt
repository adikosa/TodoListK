package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.TodoService

interface EditTodoUseCase {
    fun invoke(todo: Todo, todoId: Long): Todo
}

class EditTodoUseCaseImpl(
        private val todoService: TodoService
) : EditTodoUseCase {
    override fun invoke(todo: Todo, todoId: Long): Todo {
        // TODO: 07-Nov-20 finish later
        return todoService.save(todo)
    }
}
