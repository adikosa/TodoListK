package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksListsResponse(
        val items: List<GoogleTasksList>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksList(
        val id: String
)
