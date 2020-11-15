package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService
import java.lang.RuntimeException

interface UpdateTodoUseCase {
    fun invoke(userId: String, todoData: TodoData, todoId: String): TodoData
}

class UpdateTodoUseCaseImpl(
    private val todoService: TodoService
) : UpdateTodoUseCase {
    override fun invoke(userId: String, todoData: TodoData, todoId: String): TodoData {
        if (!todoService.isUserTodoCreator(userId, todoId)) {
            throw RuntimeException("Unauthorized todo update")
        }

        return todoService.update(todoData, todoId)
    }

}
