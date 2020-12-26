@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "todos")
data class TodoEntity(
        var title: String,
        var description: String,
        var dueDateTime: ZonedDateTime,
        @ManyToOne var user: UserEntity,
        @ManyToOne var priority: PriorityEntity,
        var isDone: Boolean = false,
        var completed: ZonedDateTime? = null
) : AbstractEntity()
