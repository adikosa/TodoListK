package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateTaskListRequest(
        val title: String
)