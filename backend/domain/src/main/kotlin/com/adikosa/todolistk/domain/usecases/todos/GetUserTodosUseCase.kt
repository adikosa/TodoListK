package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.TodoService
import com.adikosa.todolistk.domain.model.Todo

interface GetUserTodosUseCase {
    fun invoke(userId: Long): List<Todo>
}

class GetUserTodosUseCaseImpl(
     private val todoService: TodoService
) : GetUserTodosUseCase {
    override fun invoke(userId: Long): List<Todo> {
        return todoService.getAllByUserId(userId)
    }
}
