package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateNonCompletedTaskRequest(
        val title: String,
        val notes: String,
        val status: String,
        val due: String,
        val deleted: Boolean = false,
        val hidden: Boolean = false
)