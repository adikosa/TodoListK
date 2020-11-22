package com.adikosa.todolistk.domain.services

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface EmailService {
    fun sendHtmlMessage(to: String, subject: String, htmlBody: String)
    fun sendEmailActivationMessage(to: String, recipientName: String, accountActivationToken: String)
}