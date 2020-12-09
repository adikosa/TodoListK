package com.adikosa.todolistk.network.googleapi

import com.adikosa.todolistk.network.model.*
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

interface GoogleApiRepository {
    fun createTaskList(token: String, title: String): GoogleTasksListResponse
    fun getTasksLists(token: String): GoogleTasksListsResponse
    fun deleteTaskList(token: String, taskListId: String)
    fun addTaskToTaskList(token: String, taskListId: String, createTaskRequest: CreateTaskRequest): CreateTaskResponse
}

@Component
class GoogleApiRepositoryImpl(
        private val restTemplate: RestTemplate
): GoogleApiRepository {

    override fun createTaskList(token: String, title: String): GoogleTasksListResponse {
        val request = CreateTaskListRequest(title)
        return restTemplate.request(token, TASK_LISTS_URL, HttpMethod.POST, request)
    }

    override fun getTasksLists(token: String): GoogleTasksListsResponse {
        return restTemplate.request<Any, GoogleTasksListsResponse>(token, TASK_LISTS_URL, HttpMethod.GET)
    }

    override fun deleteTaskList(token: String, taskListId: String) {
        restTemplate.request<Any, Any>(token, "$TASK_LISTS_URL/$taskListId", HttpMethod.DELETE)
    }

    override fun addTaskToTaskList(token: String, taskListId: String, createTaskRequest: CreateTaskRequest): CreateTaskResponse {
        return restTemplate.request(token, TASKS_INSERT_URL.format(taskListId), HttpMethod.POST, createTaskRequest)
    }

    private inline fun <REQUEST, reified RESPONSE> RestTemplate.request(
            token: String, url: String, httpMethod: HttpMethod, requestBody: REQUEST? = null): RESPONSE {
        val headers = BASE_HEADERS.apply {
            set("Authorization", "Bearer $token")
        }
        val httpEntity = HttpEntity(requestBody, headers);
        val response = exchange<RESPONSE>(url, httpMethod, httpEntity, RESPONSE::class.java)
        return response.body!!
    }

    companion object {
        val BASE_HEADERS = HttpHeaders().apply {
            accept = listOf(MediaType.APPLICATION_JSON)
            contentType = MediaType.APPLICATION_JSON
        }

        const val TASK_LISTS_URL = "https://tasks.googleapis.com/tasks/v1/users/@me/lists"
        const val TASKS_INSERT_URL = "https://tasks.googleapis.com/tasks/v1/lists/%s/tasks"
    }
}