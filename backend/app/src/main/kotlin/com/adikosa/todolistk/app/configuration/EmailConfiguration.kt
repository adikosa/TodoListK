package com.adikosa.todolistk.app.configuration

import java.util.concurrent.Executor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver


@Configuration
class EmailConfiguration {

    @Value("\${mail.host}")
    private lateinit var hostValue: String

    @Value("\${mail.port}")
    private var portValue: Int = 0

    @Value("\${mail.username}")
    private lateinit var usernameValue: String

    @Value("\${mail.password}")
    private lateinit var passwordValue: String

    @Value("\${mail.templates.path}")
    private val mailTemplatesPath: String? = null

    @Bean("emailTaskExecutor")
    fun emailTaskExecutor(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 2
            maxPoolSize = 2
            setQueueCapacity(500)
            setThreadNamePrefix("EmailTaskExecutorThread-")
            initialize()
        }
    }

    @Bean
    fun javaMailSender(): JavaMailSenderImpl {
        val mailSender = JavaMailSenderImpl().apply {
            host = hostValue
            port = portValue
            username = usernameValue
            password = passwordValue
        }
        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.debug"] = "true"
        return mailSender
    }

    @Bean
    fun thymeleafTemplateEngine(): SpringTemplateEngine? {
        return SpringTemplateEngine().apply {
            setTemplateResolver(thymeleafClassLoaderTemplateResolver())
            setTemplateEngineMessageSource(emailMessageSource())
        }
    }

    @Bean
    fun thymeleafClassLoaderTemplateResolver(): ITemplateResolver? {
        return ClassLoaderTemplateResolver().apply {
            prefix = mailTemplatesPath.toString() + "/"
            suffix = ".html"
            setTemplateMode("HTML")
            characterEncoding = "UTF-8"
        }
    }

    @Bean
    fun emailMessageSource(): ResourceBundleMessageSource? {
        return ResourceBundleMessageSource().apply {
            setBasename("mailMessages")
        }
    }
}