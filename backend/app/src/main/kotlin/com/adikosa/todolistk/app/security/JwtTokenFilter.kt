package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.JwtTokenManager
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
//import mu.KotlinLogging
import org.springframework.web.filter.GenericFilterBean

//private val logger = KotlinLogging.logger {}

class JwtTokenFilter(
        private val tokenManager: JwtTokenManager,
        private val authManager: AuthManager
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
//        logger.debug("doFilter started")

        val httpServletRequest = request as HttpServletRequest
        val bearerToken = getBearerToken(httpServletRequest)

        if (bearerToken != null) {
            tokenManager.validate(bearerToken)
            val username = tokenManager.getUsername(bearerToken)
            authManager.authenticate(username)
        }

        chain?.doFilter(request, response)
    }

    private fun getBearerToken(httpServletRequest: HttpServletRequest): String? {
        val bearerToken: String = httpServletRequest.getHeader(AUTHORIZATION_HEADER) ?: return null

        println("Authorization header is ok")
        if (!bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
            throw RuntimeException("Invalid Bearer token")
        }

        return bearerToken.substring(BEARER_TOKEN_PREFIX.length)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER_TOKEN_PREFIX = "Bearer "
    }
}
