package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.CreateTodoData
import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService

interface CreateTodoUseCase {
    fun invoke(createTodoData: CreateTodoData): TodoData
}

class CreateTodoUseCaseImpl(
        private val todoService: TodoService
) : CreateTodoUseCase {
    override fun invoke(createTodoData: CreateTodoData): TodoData {
        return todoService.save(createTodoData)
    }
}
