package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.CreateTodoData
import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.CurrentUser
import com.adikosa.todolistk.domain.services.TodoService
import com.adikosa.todolistk.storage.PriorityRepository
import com.adikosa.todolistk.storage.TodoRepository
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.TodoEntity
import java.util.*
import kotlin.RuntimeException
import org.springframework.stereotype.Service

@Service("todoService")
class TodoServiceImpl(
        private val todoRepository: TodoRepository,
        private val priorityRepository: PriorityRepository,
        private val userRepository: UserRepository,
        private val currentUser: CurrentUser
) : TodoService {
    override fun existsById(todoId: UUID): Boolean {
        return todoRepository.existsById(todoId)
    }

    override fun findAllByUserId(userId: UUID): List<TodoData> {
        return todoRepository.findAllByUser_Id(userId).toDomain()
    }

    override fun save(createTodoData: CreateTodoData): TodoData {
        val todo = createTodoData.toEntity()
        return todoRepository.save(todo).toDomain()
    }

    override fun isUserTodoCreator(userId: UUID, todoId: UUID): Boolean {
        return todoRepository.existsByIdAndUser_Id(todoId, userId)
    }

    override fun deleteById(todoId: UUID) {
        todoRepository.deleteById(todoId)
    }

    override fun update(todoData: TodoData, todoId: UUID): TodoData {
        val todo = todoRepository.findById(todoId).orElseThrow { RuntimeException("Todo not found") }
        val todoPriority = priorityRepository.findByName(todoData.priority!!)?: throw RuntimeException("Priority $todoData.priority!! not found")
        todo.apply {
            title = todoData.title
            description = todoData.description
            dueDateTime = todoData.dueDateTime
            priority = todoPriority
        }
        return todoRepository.save(todo).toDomain()
    }

    private fun CreateTodoData.toEntity(): TodoEntity {
        val user = userRepository.findById(currentUser.id).orElseThrow { RuntimeException("User ${currentUser.id} not found") }
        val priority = priorityRepository.findByName(priority)?: throw RuntimeException("Priority $priority!! not found")
        return TodoEntity(title, description, dueDateTime, isDone, user, priority)
    }
}

fun List<TodoEntity>.toDomain(): List<TodoData> {
    return map { it.toDomain() }
}

fun TodoEntity.toDomain(): TodoData {
    return TodoData(id, title, description, dueDateTime, isDone, user.id!!, priority?.name, createdAt)
}
