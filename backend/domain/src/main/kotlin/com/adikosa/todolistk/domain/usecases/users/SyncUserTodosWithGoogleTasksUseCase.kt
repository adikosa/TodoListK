package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.CurrentUser
import com.adikosa.todolistk.domain.services.GoogleTasksService
import com.adikosa.todolistk.domain.services.TodoService

interface SyncUserTodosWithGoogleTasksUseCase {
    fun invoke(googleTasksToken: String)
}

class SyncUserTodosWithGoogleTasksUseCaseImpl(
        private val currentUser: CurrentUser,
        private val todoService: TodoService,
        private val googleTasksService: GoogleTasksService
) : SyncUserTodosWithGoogleTasksUseCase {
    override fun invoke(googleTasksToken: String) {
        val currentUserTodos = todoService.findAllByUserId(currentUser.id)
        var taskListId = googleTasksService.getTaskListId(googleTasksToken, TODOLISTK_GOOGLE_TASKS_LIST_NAME)
        if (taskListId != null) {
            googleTasksService.removeTaskList(googleTasksToken, taskListId)
        }

        taskListId = googleTasksService.createTaskList(googleTasksToken, TODOLISTK_GOOGLE_TASKS_LIST_NAME)

        for (todo in currentUserTodos) {
            googleTasksService.addTodoToTaskList(googleTasksToken, taskListId!!, todo)
        }
    }

    companion object {
        const val TODOLISTK_GOOGLE_TASKS_LIST_NAME = "TodoListK"
    }
}