package com.adikosa.todolistk.domain.model

import java.time.LocalDateTime

data class UpdateTodoData(
        var title: String?,
        var description: String?,
        var dueDateTime: LocalDateTime?,
        var priority: String?,
        var completed: LocalDateTime?,
        var isDone: Boolean?
)