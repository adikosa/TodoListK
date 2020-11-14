@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
        var firstName: String,
        var lastName: String,
        val username: String,
        var email: String,
        var password: String,
        @OneToMany(fetch = FetchType.EAGER)
        var roles: Set<RoleEntity> = emptySet(),
        var isExpired: Boolean = false,
        var isLocked: Boolean = false,
        var isCredentialsExpired: Boolean = false,
        var isEnabled: Boolean = true
) : AbstractEntity()
