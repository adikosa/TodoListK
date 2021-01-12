package com.adikosa.todolistk.app.service

import com.adikosa.todolistk.app.utils.ServerHelper
import com.adikosa.todolistk.domain.services.EmailService
import javax.mail.MessagingException
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class EmailServiceImpl(
        private val thymeleafTemplateEngine: SpringTemplateEngine,
        private val emailSender: JavaMailSender,
        private val serverHelper: ServerHelper
) : EmailService {

    @Value("\${mail.username}")
    private lateinit var emailSenderAddress: String

    @Async("emailTaskExecutor")
    override fun sendHtmlMessage(to: String, subject: String, htmlBody: String) {
        try {
            val message = emailSender.createMimeMessage()
            MimeMessageHelper(message, true, "UTF-8").apply {
                setFrom(emailSenderAddress)
                setTo(to)
                setSubject(subject)
                setText(htmlBody, true)
            }
            emailSender.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }

    @Async("emailTaskExecutor")
    override fun sendEmailActivationMessage(to: String, recipientName: String, accountActivationToken: String) {
        val thymeleafContext = Context().apply {
            setVariable(EMAIL_ACTIVATION_GREETINGS_KEY, "Greetings $recipientName, welcome onboard!")
            setVariable(EMAIL_ACTIVATION_LINK_KEY, "${serverHelper.address}/api/activate?token=$accountActivationToken")
        }
        val htmlBody: String = thymeleafTemplateEngine.process("email-template.html", thymeleafContext)
        sendHtmlMessage(to, EMAIL_ACTIVATION_SUBJECT, htmlBody)
    }

    companion object {
        const val EMAIL_ACTIVATION_SUBJECT = "TodoListK activation"
        const val EMAIL_ACTIVATION_GREETINGS_KEY = "greetings"
        const val EMAIL_ACTIVATION_LINK_KEY = "activation_link"
    }
}
