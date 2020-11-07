package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime

data class Todo(
        val id: Long?,
        val title: String,
        val description: String,
        val dueDateTime: LocalDateTime,
        val priority: String?,
        val userId: Long?
)
