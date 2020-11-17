package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService
import java.util.*

interface GetUserTodosUseCase {
    fun invoke(userUUID: UUID): List<TodoData>
}

class GetUserTodosUseCaseImpl(
     private val todoService: TodoService
) : GetUserTodosUseCase {
    override fun invoke(userUUID: UUID): List<TodoData> {
        return todoService.findAllByUserId(userUUID)
    }
}
