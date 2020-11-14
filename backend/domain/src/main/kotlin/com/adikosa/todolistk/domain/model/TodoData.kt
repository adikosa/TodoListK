package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime

data class TodoData(
        var uuid: String?,
        var title: String,
        var description: String,
        var dueDateTime: LocalDateTime,
        var userUUID: String,
        var priority: String?,
        var createdAt: LocalDateTime
)