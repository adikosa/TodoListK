package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService

interface EditTodoUseCase {
    fun invoke(todo: TodoData, todoUUID: String): TodoData
}

class EditTodoUseCaseImpl(
    private val todoService: TodoService
) : EditTodoUseCase {
    override fun invoke(todo: TodoData, todoUUID: String): TodoData {
        return todoService.save(todo)
    }

}
