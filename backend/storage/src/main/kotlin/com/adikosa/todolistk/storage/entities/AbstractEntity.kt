package com.adikosa.todolistk.storage.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue
    lateinit var id: UUID
    @CreationTimestamp
    lateinit var createdAt: LocalDateTime
    @UpdateTimestamp
    lateinit var updatedAt: LocalDateTime
}