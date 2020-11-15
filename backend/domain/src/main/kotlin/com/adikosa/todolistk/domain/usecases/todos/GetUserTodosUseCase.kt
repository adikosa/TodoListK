package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService

interface GetUserTodosUseCase {
    fun invoke(userUUID: String): List<TodoData>
}

class GetUserTodosUseCaseImpl(
     private val todoService: TodoService
) : GetUserTodosUseCase {
    override fun invoke(userUUID: String): List<TodoData> {
        return todoService.findAllByUserId(userUUID)
    }
}
