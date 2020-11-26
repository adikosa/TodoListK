package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.model.UrlResponse
import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.usecases.users.GetGoogleTasksOAuthUrlUseCase
import com.adikosa.todolistk.domain.usecases.users.SyncUserTodosWithGoogleTasksUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@IsAuthenticated
@RestController
@RequestMapping("/api/todos")
class GoogleTasksController(
        private val getGoogleTasksOAuthUrlUseCase: GetGoogleTasksOAuthUrlUseCase,
        private val syncUserTodosWithGoogleTasksUseCase: SyncUserTodosWithGoogleTasksUseCase
) {

    @GetMapping("/getGoogleTasksOAuthUrl")
    fun getGoogleTasksOAuthUrl(): ResponseEntity<UrlResponse> {
        return ok(UrlResponse(getGoogleTasksOAuthUrlUseCase.invoke()))
    }

    @GetMapping("/syncWithGoogleTasks")
    fun syncUserTodosWithGoogleTasks() {
        syncUserTodosWithGoogleTasksUseCase.invoke()
    }
}