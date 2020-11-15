package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService
import com.adikosa.todolistk.storage.PriorityRepository
import com.adikosa.todolistk.storage.TodoRepository
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.TodoEntity
import com.adikosa.todolistk.storage.toUUID
import kotlin.RuntimeException
import org.springframework.stereotype.Service

@Service("todoService")
class TodoServiceImpl(
        private val todoRepository: TodoRepository,
        private val priorityRepository: PriorityRepository,
        private val userRepository: UserRepository
) : TodoService {
    override fun existsById(todoId: String): Boolean {
        return todoRepository.existsById(todoId.toUUID())
    }

    override fun findAllByUserId(userId: String): List<TodoData> {
        return todoRepository.findAllByUser_Id(userId.toUUID()).toDomain()
    }

    override fun save(todoData: TodoData): TodoData {
        val todo = todoData.toEntity()
        return todoRepository.save(todo).toDomain()
    }

    override fun isUserTodoCreator(userId: String, todoId: String): Boolean {
        return todoRepository.existsByIdAndUser_Id(todoId.toUUID(), userId.toUUID())
    }

    override fun deleteById(todoId: String) {
        todoRepository.deleteById(todoId.toUUID())
    }

    override fun update(todoData: TodoData, todoId: String): TodoData {
        val todo = todoRepository.findById(todoId.toUUID()).orElseThrow { RuntimeException("Todo not found") }
        val todoPriority = priorityRepository.findByName(todoData.priority!!)?: throw RuntimeException("Priority $todoData.priority!! not found")
        todo.apply {
            title = todoData.title
            description = todoData.description
            dueDateTime = todoData.dueDateTime
            priority = todoPriority
        }
        return todoRepository.save(todo).toDomain()
    }

    private fun TodoData.toEntity(): TodoEntity {
        val user = userRepository.findById(userId.toUUID()).orElseThrow { RuntimeException("User not found") }
        val priority = priorityRepository.findByName(priority!!)?: throw RuntimeException("Priority $priority!! not found")
        return TodoEntity(title, description, dueDateTime, user, priority)
    }
}

fun List<TodoEntity>.toDomain(): List<TodoData> {
    return map { it.toDomain() }
}

fun TodoEntity.toDomain(): TodoData {
    return TodoData(id.toString(), title, description, dueDateTime, user.id.toString(), priority?.name, createdAt)
}
