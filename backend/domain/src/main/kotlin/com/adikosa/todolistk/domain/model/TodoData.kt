package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

data class TodoData(
        var id: UUID?,
        var title: String,
        var description: String,
        var dueDateTime: ZonedDateTime,
        var isDone: Boolean,
        var userId: UUID,
        var priority: String,
        val completed: ZonedDateTime?,
        var createdAt: ZonedDateTime?
)