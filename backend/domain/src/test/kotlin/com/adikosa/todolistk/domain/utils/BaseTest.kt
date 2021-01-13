package com.adikosa.todolistk.domain.utils

import org.junit.jupiter.api.BeforeEach
import org.mockito.MockitoAnnotations

abstract class BaseTest {
    @BeforeEach
    internal fun baseSetup() {
        MockitoAnnotations.initMocks(this)
    }
}