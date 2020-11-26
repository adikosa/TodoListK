@file:Suppress("JpaDataSourceORMInspection")

package com.adikosa.todolistk.storage.entities

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
        var firstName: String,
        var lastName: String,
        val username: String,
        var email: String,
        var password: String,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Set<RoleEntity> = emptySet(),
        var isExpired: Boolean = false,
        var isLocked: Boolean = false,
        var isCredentialsExpired: Boolean = false,
        var isEnabled: Boolean = true,
        var token: String? = null
) : AbstractEntity()
