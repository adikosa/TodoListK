package com.adikosa.todolistk.domain.usecases.users

import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.domain.utils.BaseTest
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import java.util.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

class ActivateUserAccountUseCaseImplTest : BaseTest() {

    @Mock
    lateinit var userService: UserService

    lateinit var activateAccountUseCase: ActivateUserAccountUseCaseImpl

    @BeforeEach
    internal fun setUp() {
        activateAccountUseCase = ActivateUserAccountUseCaseImpl(userService)
    }

    @Test
    internal fun `should activate user account`() {
        val userId = UUID.randomUUID()

        activateAccountUseCase.invoke(userId)

        verify(userService, times(1)).activate(userId)
        verifyNoMoreInteractions(userService)
    }
}