package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.app.utils.plusMillis
import com.adikosa.todolistk.domain.DateProvider
import com.adikosa.todolistk.domain.services.JwtTokenManager
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SpringJwtTokenManager(
        base64Encoder: Base64.Encoder,
        private val dateProvider: DateProvider
) : JwtTokenManager {

    @Value("\${security.jwt.token.secret-key}")
    private lateinit var secretKey: String

    init {
        secretKey = base64Encoder.encodeToString(secretKey.toByteArray())
    }

    override fun createToken(username: String): String {
        val now = dateProvider.now()
        val expiration = now.plusMillis(TOKEN_VALIDITY)

        return Jwts.builder().apply {
            setSubject(username) // TODO: 14-Nov-20 change with key-value store later
            setIssuedAt(now)
            setExpiration(expiration)
            signWith(SIGNATURE_ALGORITHM, secretKey)
        }.compact()
    }

    override fun isValid(token: String): Boolean {
        val claims = getClaims(token)
        return claims.body.expiration.after(dateProvider.now())
    }

    override fun getUsername(token: String): String {
        val claims = getClaims(token)
        return claims.body.subject
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
    }

    companion object {
        const val TOKEN_VALIDITY: Long = 3600000
        val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256
    }
}
