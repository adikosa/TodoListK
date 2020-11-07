@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage

import com.adikosa.todolistk.domain.model.Priority
import com.adikosa.todolistk.domain.model.Todo
import com.adikosa.todolistk.domain.model.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity<DOMAIN> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
    @CreationTimestamp lateinit var createdAt: LocalDateTime
    @UpdateTimestamp lateinit var updatedAt: LocalDateTime

    abstract fun toDomain(): DOMAIN
}

fun <T> List<AbstractEntity<T>>.toDomain(): List<T> {
    return map { it.toDomain() }
}

@Entity
@Table(name = "users")
class UserEntity(
        var firstName: String,
        var lastName: String,
        var username: String,
        var password: String,
        var token: String?,
        var isExpired: Boolean = false,
        var isLocked: Boolean = false,
        var isCredentialsExpired: Boolean = false,
        var isEnabled: Boolean = true
) : AbstractEntity<User>() {
    override fun toDomain(): User {
        return User(id, firstName, lastName, username, password, token)
    }
}

@Entity
@Table(name = "todos")
data class TodoEntity(
    var title: String,
    var description: String,
    var dueDateTime: LocalDateTime,
    @ManyToOne var user: UserEntity,
    @ManyToOne var priority: PriorityEntity?
) : AbstractEntity<Todo>() {
    override fun toDomain(): Todo {
        return Todo(id, title, description, dueDateTime, priority?.name, user.id)
    }
}

@Entity
@Table(name = "priorities")
data class PriorityEntity(
        @Column(nullable = false)
        var name: String
) : AbstractEntity<Priority>() {
    override fun toDomain(): Priority {
        return Priority(name)
    }

}