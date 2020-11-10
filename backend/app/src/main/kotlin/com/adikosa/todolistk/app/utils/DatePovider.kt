package com.adikosa.todolistk.app.utils

import com.adikosa.todolistk.domain.DateProvider
import java.util.*
import org.springframework.stereotype.Component

@Component
class DateProviderImpl : DateProvider {
    override fun now() = Date()
}

fun Date.plusMillis(millis: Long) = Date(time + millis)
