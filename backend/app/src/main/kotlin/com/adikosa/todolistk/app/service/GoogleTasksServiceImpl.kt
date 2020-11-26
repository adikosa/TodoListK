package com.adikosa.todolistk.app.service

import com.adikosa.todolistk.domain.services.GoogleTasksService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleTasksServiceImpl : GoogleTasksService {

    @Value("\${google.client-id}")
    private lateinit var clientId: String

    @Value("\${google.client-secret}")
    private lateinit var secretKey: String

    @Value("\${google.redirect-uri}")
    private lateinit var redirectUri: String

    override val oAuthUrl: String
        get() = "https://accounts.google.com/o/oauth2/auth?access_type=online&client_id=$clientId" +
                "&redirect_uri=$redirectUri&response_type=token"



}