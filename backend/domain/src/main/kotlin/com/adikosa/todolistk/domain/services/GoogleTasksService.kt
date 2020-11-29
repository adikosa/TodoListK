package com.adikosa.todolistk.domain.services

import com.adikosa.todolistk.domain.model.TodoData

interface GoogleTasksService {
    val oAuthUrl: String
    fun getTaskListId(token: String, title: String): String?
    fun removeTaskList(googleTasksToken: String, taskListId: String)
    fun createTaskList(googleTasksToken: String, taskListTitle: String): String?
    fun addTodoToTaskList(googleTasksToken: String, taskListId: String, todo: TodoData)
}
