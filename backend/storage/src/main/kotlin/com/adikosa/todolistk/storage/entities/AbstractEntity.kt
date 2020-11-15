package com.adikosa.todolistk.storage.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@MappedSuperclass
abstract class AbstractEntity(
        @Id @GeneratedValue var id: UUID? = null,
        @CreationTimestamp var createdAt: LocalDateTime? = null,
        @UpdateTimestamp var updatedAt: LocalDateTime? = null
)
