@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "priorities")
data class PriorityEntity(
        @Column(nullable = false)
        var name: String
) : AbstractEntity()