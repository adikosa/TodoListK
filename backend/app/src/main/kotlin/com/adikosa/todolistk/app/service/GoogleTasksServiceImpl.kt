package com.adikosa.todolistk.app.service

import com.adikosa.todolistk.domain.model.TodoData
import com.adikosa.todolistk.domain.services.GoogleTasksService
import com.adikosa.todolistk.network.googleapi.GoogleApiRepository
import com.adikosa.todolistk.network.model.CreateTaskRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class GoogleTasksServiceImpl(
    private val googleApiRepository: GoogleApiRepository
) : GoogleTasksService {

    @Value("\${google.client-id}")
    private lateinit var clientId: String

    @Value("\${google.client-secret}")
    private lateinit var secretKey: String

    @Value("\${google.redirect-uri}")
    private lateinit var redirectUri: String

    override val oAuthUrl: String
        get() = "https://accounts.google.com/o/oauth2/auth?access_type=online&client_id=$clientId" +
                "&redirect_uri=$redirectUri&response_type=token&scope=https://www.googleapis.com/auth/tasks"

    override fun getTaskListId(token: String, title: String): String? {
        return googleApiRepository.getTasksLists(token).items.find { list -> list.title == title }?.id
    }

    override fun removeTaskList(googleTasksToken: String, taskListId: String) {
        googleApiRepository.deleteTaskList(googleTasksToken, taskListId)
    }

    override fun createTaskList(googleTasksToken: String, taskListTitle: String): String {
        return googleApiRepository.createTaskList(googleTasksToken, taskListTitle).id
    }

    override fun addTodoToTaskList(googleTasksToken: String, taskListId: String, todo: TodoData) {
        googleApiRepository.addTaskToTaskList(googleTasksToken, taskListId, todo.toNetwork())
    }
}

val formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneId.of("UTC"))

fun TodoData.toNetwork() = CreateTaskRequest(
        title = title,
        notes = description,
        status = if (isDone) "completed" else "needsAction",
        due = formatter.format(dueDateTime).toString(),
        completed = if (completed == null) null else formatter.format(completed).toString()
)