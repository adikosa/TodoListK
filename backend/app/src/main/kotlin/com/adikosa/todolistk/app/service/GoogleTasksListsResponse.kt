package com.adikosa.todolistk.app.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksListsResponse(
        val items: List<GoogleTasksList>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksList(
        val id: String
)
