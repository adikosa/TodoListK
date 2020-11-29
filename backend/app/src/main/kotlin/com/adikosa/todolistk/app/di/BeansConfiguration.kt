package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.services.*
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.UpdateTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.CreateTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.CreateTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.*
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
@ComponentScan(basePackages = ["com.adikosa.todolistk.storage", "com.adikosa.todolistk.network"])
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
    fun saveTodoUseCaseFactory(todoService: TodoService): CreateTodoUseCase = CreateTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun editTodoUseCaseFactory(currentUser: CurrentUser, todoService: TodoService): UpdateTodoUseCase {
        return UpdateTodoUseCaseImpl(currentUser, todoService)
    }

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
    fun logoutUseCaseFactory(currentUser: CurrentUser, userService: UserService): LogoutUseCase {
        return LogoutUseCaseImpl(currentUser, userService)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun activateUserAccountUseCaseFactory(userService: UserService): ActivateUserAccountUseCase {
        return ActivateUserAccountUseCaseImpl(userService)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun getGoogleTasksOAuthUrlUseCaseFactory(googleTasksService: GoogleTasksService): GetGoogleTasksOAuthUrlUseCase {
        return GetGoogleTasksOAuthUrlUseCaseImpl(googleTasksService)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun syncUserTodosWithGoogleTasksUseCaseFactory(currentUser: CurrentUser, todoService: TodoService,
                                                   googleTasksService: GoogleTasksService
    ): SyncUserTodosWithGoogleTasksUseCase {
        return SyncUserTodosWithGoogleTasksUseCaseImpl(currentUser, todoService, googleTasksService)
    }

}

