package com.adikosa.todolistk.app.service

import com.adikosa.todolistk.domain.services.TasksService
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.tasks.Tasks
import com.google.api.services.tasks.TasksScopes
import com.google.api.services.tasks.model.TaskLists
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStreamReader

@Service
class GoogleTasksService : TasksService {

    @Value("\${google.client-id}")
    private lateinit var clientId: String

    @Value("\${google.client-secret}")
    private lateinit var secretKey: String

    @Value("\${google.redirect-uri}")
    private lateinit var redirectUri: String

    val oAuthUrl: String
        get() = "https://accounts.google.com/o/oauth2/auth?access_type=online&client_id=$clientId" +
                "&redirect_uri=$redirectUri&response_type=token"

    private fun getCredentials(httpTransport: NetHttpTransport): Credential? {
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                InputStreamReader(ClassPathResource("credentials.json").inputStream)
        )
        val flow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build()
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    fun sync() {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val service: Tasks = Tasks.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build()

        println("Service $service")

        val todoListKTaskList = service.tasklists().get("TodoListK")
        println(todoListKTaskList)

        // Print the first 10 task lists.
        val result: TaskLists = service.tasklists().list()
                .setMaxResults(10L)
                .execute()

        val taskLists = result.items
        if (taskLists == null || taskLists.isEmpty()) {
            println("No task lists found.")
        } else {
            println("Task lists:")
            for (tasklist in taskLists) {
                System.out.printf("%s (%s)\n", tasklist.title, tasklist.id)
            }
        }
    }

    companion object {
        const val APPLICATION_NAME = "TodoListK"
        val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()
        var SCOPES = listOf(TasksScopes.TASKS_READONLY)
        const val TOKENS_DIRECTORY_PATH = "tokens"
    }

}