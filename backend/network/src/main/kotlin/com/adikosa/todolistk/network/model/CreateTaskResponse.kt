package com.adikosa.todolistk.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateTaskResponse(
        val kind: String,
        val id: String,
        val etag: String,
        val title: String,
        val updated: String,
        val selfLink: String,
        val parent: String,
        val position: String,
        val notes: String,
        val status: String,
        val due: String,
        val completed: String,
        val deleted: Boolean,
        val hidden: Boolean
)