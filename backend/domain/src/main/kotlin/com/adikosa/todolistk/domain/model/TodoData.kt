package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime

data class TodoData(
        var id: String?,
        var title: String,
        var description: String,
        var dueDateTime: LocalDateTime,
        var userId: String,
        var priority: String?,
        var createdAt: LocalDateTime?
)