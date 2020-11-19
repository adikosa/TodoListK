package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.model.LoginData
import com.adikosa.todolistk.domain.services.AuthManager
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService

class SpringAuthManager(
        private val authenticationManager: AuthenticationManager,
        private val userDetailsService: UserDetailsService
) : AuthManager {

    override fun authenticate(username: String) {
        val userDetails = userDetailsService.loadUserByUsername(username) // TODO: 14-Nov-20 Change later to loadUserByUUID?

        if (userDetails.isEnabled && userDetails.isAccountNonLocked) {
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
            getSecurityContext().authentication = authenticationToken
        }
    }

    override fun authenticate(loginData: LoginData) {
        val authenticationToken = with(loginData) { UsernamePasswordAuthenticationToken(username, password) }
        authenticationManager.authenticate(authenticationToken)
    }

    // TODO: 19-Nov-20 think about better solution later
    private fun getSecurityContext(): SecurityContext {
        return SecurityContextHolder.getContext()
    }
}
