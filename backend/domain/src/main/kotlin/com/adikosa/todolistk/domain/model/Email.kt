package com.adikosa.todolistk.domain.model

data class Email(
    val from: String,
    val to: String,
    val subject: String,
    val text: String,
    val isMultipart: Boolean
)