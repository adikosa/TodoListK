package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.model.CreateTodoData
import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.model.UpdateTodoData
import com.adikosa.todolistk.domain.services.CurrentUser
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.CreateTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCase
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@IsAuthenticated
@RestController
@RequestMapping("/api/todos")
class TodoController(
        private val currentUser: CurrentUser,
        private val createTodoUseCase: CreateTodoUseCase,
        private val deleteTodoUseCase: DeleteTodoUseCase,
        private val updateTodoUseCase: UpdateTodoUseCase,
        private val getUserTodosUseCase: GetUserTodosUseCase
) {
    @PostMapping
    fun save(@RequestBody createTodoData: CreateTodoData): TodoData {
        return createTodoUseCase.invoke(createTodoData)
    }

    @GetMapping
    fun getCurrentUserTodos(): List<TodoData> {
        return getUserTodosUseCase.invoke(currentUser.id)
    }

    @PatchMapping("/{todoId}")
    fun updateCurrentUserTodo(@RequestBody updateTodoData: UpdateTodoData, @PathVariable todoId: UUID): TodoData {
        return updateTodoUseCase.invoke(updateTodoData, todoId)
    }

    @DeleteMapping
    fun deleteById(@RequestParam todoId: UUID) {
        deleteTodoUseCase.invoke(todoId)
    }
}

