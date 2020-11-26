package com.adikosa.todolistk.app.controller

import com.adikosa.todolistk.app.service.GoogleTasksService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
//@IsAuthenticated
@RestController
@RequestMapping("/api/todos")
class GoogleTasksController {
    @Autowired
    lateinit var googleTasksService: GoogleTasksService

    @GetMapping("/syncWithGoogleTasks")
    fun syncWithGoogleTasks() {
        "https://accounts.google.com/o/oauth2/auth?access_type=offline&client_id=261748062147-hjdl8ph14mob1p9l79hjc24j64goqhem.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/todos/syncWithGoogleTasks&response_type=code&scope=https://www.googleapis.com/auth/tasks.readonly"
        googleTasksService.sync()
    }
}