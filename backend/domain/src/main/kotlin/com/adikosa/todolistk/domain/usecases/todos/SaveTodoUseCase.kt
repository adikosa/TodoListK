package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService

interface SaveTodoUseCase {
    fun invoke(todo: TodoData): TodoData
}

class SaveTodoUseCaseImpl(
        private val todoService: TodoService
) : SaveTodoUseCase {
    override fun invoke(todo: TodoData): TodoData {
        return todoService.save(todo)
    }
}
