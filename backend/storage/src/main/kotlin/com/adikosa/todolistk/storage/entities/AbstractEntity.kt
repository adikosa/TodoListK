package com.adikosa.todolistk.storage.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@MappedSuperclass
abstract class AbstractEntity(
        @Id @GeneratedValue var id: UUID? = null,
        @CreationTimestamp var createdAt: ZonedDateTime? = null,
        @UpdateTimestamp var updatedAt: ZonedDateTime? = null
)
