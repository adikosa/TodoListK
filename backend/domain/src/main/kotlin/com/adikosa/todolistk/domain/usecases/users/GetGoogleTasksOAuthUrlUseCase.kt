package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.GoogleTasksService

interface GetGoogleTasksOAuthUrlUseCase {
    fun invoke(): String
}

class GetGoogleTasksOAuthUrlUseCaseImpl(
        private val googleTasksService: GoogleTasksService
) : GetGoogleTasksOAuthUrlUseCase {
    override fun invoke(): String {
        return googleTasksService.oAuthUrl
    }
}