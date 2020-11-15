package com.adikosa.todolistk.app.security

import com.adikosa.todolistk.app.di.SpringAuthManager
import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.JwtTokenManager
import javax.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration()

@EnableWebSecurity
@Configuration
class HttpSecurityConfiguration(
        private val userDetailsService: UserDetailsService,
        private val passwordEncoder: PasswordEncoder,
        private val tokenManager: JwtTokenManager
) : WebSecurityConfigurerAdapter() {

    lateinit var authManager: AuthManager

    @Bean
    fun provideSecurityContext(): SecurityContext {
        return SecurityContextHolder.getContext()
    }

    @Bean
    fun provideAuthManager(): AuthManager {
        return SpringAuthManager(authenticationManagerBean(), userDetailsService, provideSecurityContext())
    }

    @PostConstruct
    fun init() {
        authManager = provideAuthManager()
    }


    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/login", "/api/register").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(JwtTokenFilter(tokenManager, authManager), UsernamePasswordAuthenticationFilter::class.java)
    }
}
