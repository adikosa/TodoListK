package com.adikosa.todolistk.app.configuration

import com.adikosa.todolistk.storage.RoleRepository
import com.adikosa.todolistk.storage.UserRepository
import com.adikosa.todolistk.storage.entities.RoleEntity
import com.adikosa.todolistk.storage.entities.UserEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DemoConfig(
        private val roleRepository: RoleRepository,
        private val userRepository: UserRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val userRole = roleRepository.save(RoleEntity("ROLE_USER"))
        val user = userRepository.save(UserEntity("Patryk", "Kosieradzki", "admin",
                "pkos@gmail.com", "\$2a\$10\$BuAAvWEA2gmzC8Uw16Kwt.SIjwygZinf96gw0qYp0RL/FVC7dyzty"))
    }

}
