package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.EmailService
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.PasswordManager
import com.adikosa.todolistk.domain.services.TodoService
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.ActivateUserAccountUseCase
import com.adikosa.todolistk.domain.usecases.users.ActivateUserAccountUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCase
import com.adikosa.todolistk.domain.usecases.users.LoginUserUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCase
import com.adikosa.todolistk.domain.usecases.users.RegisterUserUseCaseImpl
import java.util.*
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EntityScan(basePackages = ["com.adikosa.todolistk.storage"])
@ComponentScan(basePackages = ["com.adikosa.todolistk.storage"])
@EnableJpaRepositories(basePackages = ["com.adikosa.todolistk.storage"])
class BeansConfiguration {

    @Bean
    fun provideBase64Encoder(): Base64.Encoder {
        return Base64.getEncoder()
    }

    @Bean
    fun providePasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    fun providePasswordManager(passwordEncoder: PasswordEncoder): PasswordManager {
        return SpringPasswordManager(passwordEncoder)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun saveTodoUseCaseFactory(todoService: TodoService): SaveTodoUseCase = SaveTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun editTodoUseCaseFactory(todoService: TodoService): UpdateTodoUseCase = UpdateTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun getUserTodosUseCaseFactory(todoService: TodoService): GetUserTodosUseCase = GetUserTodosUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun deleteTodoUseCaseFactory(todoService: TodoService): DeleteTodoUseCase = DeleteTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun registerUserUseCaseFactory(passwordManager: PasswordManager, jwtTokenManager: JwtTokenManager,
                                        userService: UserService, emailService: EmailService): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(passwordManager, jwtTokenManager, userService, emailService)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun loginUserUseCaseFactory(jwtTokenManager: JwtTokenManager, userService: UserService,
                              authManager: AuthManager): LoginUserUseCase {
        return LoginUserUseCaseImpl(jwtTokenManager, userService, authManager)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun activateUserAccountUseCaseFactory(userService: UserService): ActivateUserAccountUseCase {
        return ActivateUserAccountUseCaseImpl(userService)
    }
}
