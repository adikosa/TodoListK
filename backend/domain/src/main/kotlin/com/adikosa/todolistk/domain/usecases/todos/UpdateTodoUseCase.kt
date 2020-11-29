package com.adikosa.todolistk.domain.usecases.todos

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.model.UpdateTodoData
import com.adikosa.todolistk.domain.services.CurrentUser
import com.adikosa.todolistk.domain.services.TodoService
import java.lang.RuntimeException
import java.util.*

interface UpdateTodoUseCase {
    fun invoke(updateTodoData: UpdateTodoData, todoId: UUID): TodoData
}

class UpdateTodoUseCaseImpl(
        private val currentUser: CurrentUser,
        private val todoService: TodoService
) : UpdateTodoUseCase {
    override fun invoke(updateTodoData: UpdateTodoData, todoId: UUID): TodoData {
        if (!todoService.isUserTodoCreator(currentUser.id, todoId)) {
            throw RuntimeException("Unauthorized todo update")
        }

        return todoService.update(updateTodoData, todoId)
    }

}
