package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksListsResponse(
        val kind: String,
        val etag: String,
        val items: List<GoogleTasksListResponse>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleTasksListResponse(
        val kind: String,
        val id: String,
        val etag: String,
        val title: String,
        val selfLink: String
)
