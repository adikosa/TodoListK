@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "roles")
data class RoleEntity(
        var name: String
) : AbstractEntity()
