@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "todos")
data class TodoEntity(
        var title: String,
        var description: String,
        var dueDateTime: LocalDateTime,
        @ManyToOne var user: UserEntity,
        @ManyToOne var priority: PriorityEntity?
) : AbstractEntity()
