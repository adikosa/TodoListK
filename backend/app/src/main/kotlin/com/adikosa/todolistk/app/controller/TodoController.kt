package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.security.CurrentUser
import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCase
import java.util.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@IsAuthenticated
@RestController
@RequestMapping("/api/todos")
class TodoController(
        private val currentUser: CurrentUser,
        private val saveTodoUseCase: SaveTodoUseCase,
        private val deleteTodoUseCase: DeleteTodoUseCase,
        private val updateTodoUseCase: UpdateTodoUseCase,
        private val getUserTodosUseCase: GetUserTodosUseCase
) {

    @PostMapping
    fun save(@RequestBody todoData: TodoData): TodoData {
        return saveTodoUseCase.invoke(todoData.apply { userId = currentUser.id })
    }

    @GetMapping
    fun getCurrentUserTodos(): List<TodoData> {
        return getUserTodosUseCase.invoke(currentUser.id)
    }

    @PatchMapping
    fun updateCurrentUserTodo(@RequestBody todoData: TodoData, @RequestParam todoId: UUID): TodoData {
        return updateTodoUseCase.invoke(currentUser.id, todoData, todoId)
    }

    @DeleteMapping
    fun deleteById(@RequestParam todoId: UUID) {
        deleteTodoUseCase.invoke(todoId)
    }

}

