package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.UserEntity
import java.lang.RuntimeException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailsServiceImpl(
        private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?: throw RuntimeException()
        return UserDetailsImpl(user)
    }
}

class UserDetailsImpl(
        private val userEntity: UserEntity
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return userEntity.roles.map { SimpleGrantedAuthority( it.name) }
    }

    override fun getUsername() = userEntity.username
    override fun getPassword() = userEntity.password

    override fun isCredentialsNonExpired() = !userEntity.isCredentialsExpired
    override fun isAccountNonExpired() = !userEntity.isExpired
    override fun isAccountNonLocked() = !userEntity.isLocked
    override fun isEnabled() = userEntity.isEnabled
}
