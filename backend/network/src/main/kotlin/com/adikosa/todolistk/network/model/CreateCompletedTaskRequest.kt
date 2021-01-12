package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
data class CreateCompletedTaskRequest(
        val title: String,
        val notes: String,
        val status: String,
        val due: String,
        val completed: String?,
        val deleted: Boolean = false,
        val hidden: Boolean = false
)