package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.domain.services.UserService
import java.lang.RuntimeException
import java.util.*
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

interface CurrentUser {
    val id: UUID
}

@Component
class CurrentUserImpl(
        private val securityContext: SecurityContext,
        private val userService: UserService
) : CurrentUser {

    override val id: UUID
        get() {
            val username = getUsername() ?: throw RuntimeException("No logged user found")
            return userService.findIdByUsername(username)
        }


    private fun getUsername(): String? {
        val principal = securityContext.authentication.principal
        if (principal is UserDetails) {
            return principal.username
        }

        return null;
    }
}

