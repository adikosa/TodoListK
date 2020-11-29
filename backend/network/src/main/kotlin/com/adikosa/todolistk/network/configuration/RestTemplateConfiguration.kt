package com.adikosa.todolistk.network.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration {
    @Bean
    fun provideRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}
