package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.app.utils.plusMillis
import com.adikosa.todolistk.domain.DateProvider
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class SpringJwtTokenManager(
        base64Encoder: Base64.Encoder,
        private val dateProvider: DateProvider,
        private val userService: UserService
) : JwtTokenManager {

    init {
        SECRET_KEY = base64Encoder.encodeToString(SECRET_KEY.toByteArray())
    }

    override fun createToken(username: String): String {
        val now = dateProvider.now()
        val expiration = now.plusMillis(TOKEN_VALIDITY)

        return Jwts.builder().apply {
            setSubject(username) // TODO: 14-Nov-20 change with key-value store later
            setIssuedAt(now)
            setExpiration(expiration)
            signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
        }.compact()
    }

    override fun validate(token: String) {
        val claims = getClaims(token)
        if (claims.body.expiration.before(dateProvider.now()) || !userService.hasUserToken(getUsername(token), token)) {
            throw RuntimeException("Invalid Jwt Token")
        }
    }

    override fun getUsername(token: String): String {
        val claims = getClaims(token)
        return claims.body.subject
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
    }

    companion object {
        private var SECRET_KEY = "secret-key"
        const val TOKEN_VALIDITY: Long = 86400000
        val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256
    }
}
