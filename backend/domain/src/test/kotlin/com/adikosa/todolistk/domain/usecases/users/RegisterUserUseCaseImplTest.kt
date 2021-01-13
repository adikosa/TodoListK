package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.exceptions.EmailAlreadyTakenException
import com.adikosa.todolistk.domain.exceptions.UsernameAlreadyTakenException
import com.adikosa.todolistk.domain.model.RegisterData
import com.adikosa.todolistk.domain.services.EmailService
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.PasswordManager
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.domain.utils.BaseTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.util.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock

class RegisterUserUseCaseImplTest : BaseTest() {

    private val uuid = UUID.randomUUID()
    private val token = "token"
    private val hashedPassword = "hashedpass"
    private val registerData =
            RegisterData("Adam", "Testowy", "atestowy", "adam@test.com", "adampass")

    private val passwordManager = mock<PasswordManager> {
           on { encode("adampass") } doReturn hashedPassword
    }

    private val jwtTokenManager = mock<JwtTokenManager> {
        on { createToken("atestowy") } doReturn token
    }

    private val userService = mock<UserService> {
        on { register(any()) } doReturn uuid
    }

    @Mock
    lateinit var emailService: EmailService

    lateinit var registerUserUseCase: RegisterUserUseCaseImpl

    @BeforeEach
    fun setUp() {
        registerUserUseCase = RegisterUserUseCaseImpl(
                passwordManager,
                jwtTokenManager,
                userService,
                emailService
        )
    }

    @Test
    fun `on email taken should throw exception`() {
        whenever(userService.isEmailTaken("adam@test.com")).thenReturn(true)

        assertThrows<EmailAlreadyTakenException> {
            registerUserUseCase.invoke(registerData)
        }
    }

    @Test
    fun `on username taken should throw exception`() {
        whenever(userService.isUsernameTaken("atestowy")).thenReturn(true)

        assertThrows<UsernameAlreadyTakenException> {
            registerUserUseCase.invoke(registerData)
        }
    }

    @Test
    fun `should register with hashed password`() {
        registerUserUseCase.invoke(registerData)

        val captor = argumentCaptor<RegisterData>()
        verify(userService).register(captor.capture())

        assertThat(captor.firstValue.password).isEqualTo(hashedPassword)
    }

    @Test
    fun `should create user token`() {
        val result = registerUserUseCase.invoke(registerData)

        assertThat(result.id).isEqualTo(uuid)
        assertThat(result.token).isEqualTo(token)
        verify(userService, times(1)).saveUserToken(uuid, token)
    }

    @Test
    fun `should send email after register`() {
        val result = registerUserUseCase.invoke(registerData)

        assertThat(result.id).isEqualTo(uuid)
        assertThat(result.token).isEqualTo(token)
        verify(userService, times(1)).saveUserToken(uuid, token)
    }
}