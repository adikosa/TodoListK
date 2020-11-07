package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.domain.model.Todo
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.EditTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(
        private val saveTodoUseCase: SaveTodoUseCase,
        private val deleteTodoUseCase: DeleteTodoUseCase,
        private val editTodoUseCase: EditTodoUseCase,
        private val getUserTodosUseCase: GetUserTodosUseCase
) {
    @PostMapping
    fun save(@RequestBody todo: Todo): Todo = saveTodoUseCase.invoke(todo)

    @GetMapping
    fun getUserTodos(@RequestParam userId: Long): List<Todo> = getUserTodosUseCase.invoke(userId)

    @DeleteMapping("/{todoId}")
    fun deleteById(@PathVariable todoId: Long) = deleteTodoUseCase.invoke(todoId)

}