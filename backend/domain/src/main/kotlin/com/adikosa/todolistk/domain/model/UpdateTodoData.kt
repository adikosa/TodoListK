package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime
import java.time.ZonedDateTime

data class UpdateTodoData(
        var title: String?,
        var description: String?,
        var dueDateTime: ZonedDateTime?,
        var priority: String?,
        var completed: ZonedDateTime?,
        var isDone: Boolean?
)