package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.model.UrlResponse
import com.adikosa.todolistk.app.security.IsAuthenticated
import com.adikosa.todolistk.domain.usecases.users.GetGoogleTasksOAuthUrlUseCase
import com.adikosa.todolistk.domain.usecases.users.SyncUserTodosWithGoogleTasksUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@CrossOrigin
@IsAuthenticated
@RestController
@RequestMapping("/api/googleTasks")
class GoogleTasksController(
        private val getGoogleTasksOAuthUrlUseCase: GetGoogleTasksOAuthUrlUseCase,
        private val syncUserTodosWithGoogleTasksUseCase: SyncUserTodosWithGoogleTasksUseCase
) {

    @GetMapping("/oAuthUrl")
    fun getGoogleTasksOAuthUrl(): ResponseEntity<UrlResponse> {
        return ok(UrlResponse(getGoogleTasksOAuthUrlUseCase.invoke()))
    }

    @PostMapping("/sync")
    fun syncUserTodosWithGoogleTasks(@RequestParam googleTasksToken: String) {
        syncUserTodosWithGoogleTasksUseCase.invoke(googleTasksToken)
    }
}