package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime

data class CreateTodoData(
        var title: String,
        var description: String,
        var dueDateTime: LocalDateTime,
        var priority: String,
        var isDone: Boolean
)