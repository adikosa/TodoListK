package com.adikosa.todolistk.storage.services

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.TodoService
import com.adikosa.todolistk.storage.PriorityRepository
import com.adikosa.todolistk.storage.TodoRepository
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.TodoEntity
import java.util.*
import org.springframework.stereotype.Service

@Service("todoService")
class TodoServiceImpl(
        private val todoRepository: TodoRepository,
        private val priorityRepository: PriorityRepository,
        private val userRepository: UserRepository
) : TodoService {
    override fun existsById(uuid: String): Boolean {
        return false
    }

    override fun getAllByUserId(userUUID: String): List<TodoData> {
        return todoRepository.findAllByUser_Id(UUID.fromString(userUUID)).toDomain()
    }

    //    override fun getAllByUserId(userId: Long): List<Todo> = todoRepository.findAllByUser_Id(userId).toDomain()
//    override fun getAll(): List<Todo> = todoRepository.findAll().toDomain()
//    override fun getById(id: Long): Todo = todoRepository.findById(id).orElseThrow().toDomain()
//    override fun save(todo: Todo): Todo = todoRepository.save(todo.toEntity()).toDomain()
//    override fun save(todos: List<Todo>): List<Todo> = todoRepository.saveAll(todos.toEntity()).toDomain()
//    override fun existsById(id: Long): Boolean = todoRepository.existsById(id)
//    override fun deleteById(id: Long) = todoRepository.deleteById(id)

    override fun save(todo: TodoData): TodoData {
        TODO("Not yet implemented")
    }
//    override fun update(todo: Todo, id: Long): Todo {
//        // TODO: 07-Nov-20 finish later
//        return save(todo)
//    }
}

fun List<TodoEntity>.toDomain(): List<TodoData> {
    return map { it.toDomain() }
}

fun TodoEntity.toDomain(): TodoData {
    return TodoData(id.toString(), title, description, dueDateTime, user.id.toString(), priority?.name, createdAt)
}
