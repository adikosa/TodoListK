package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateTaskRequest(
        val title: String,
        val notes: String,
        val status: String,
        val due: String,
        val completed: String,
        val deleted: Boolean = false,
        val hidden: Boolean = false
)