package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime
import java.util.*

data class TodoData(
        var id: UUID?,
        var title: String,
        var description: String,
        var dueDateTime: LocalDateTime,
        var isDone: Boolean,
        var userId: UUID,
        var priority: String,
        val completed: LocalDateTime?,
        var createdAt: LocalDateTime?
)