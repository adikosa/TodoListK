package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.services.AuthManager
import com.adikosa.todolistk.domain.services.JwtTokenManager
import com.adikosa.todolistk.domain.services.PasswordManager
import com.adikosa.todolistk.domain.services.TodoService
import com.adikosa.todolistk.domain.services.UserService
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.DeleteTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.EditTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.EditTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCase
import com.adikosa.todolistk.domain.usecases.todos.GetUserTodosUseCaseImpl
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCase
import com.adikosa.todolistk.domain.usecases.todos.SaveTodoUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.DeleteUserUseCase
import com.adikosa.todolistk.domain.usecases.users.DeleteUserUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.GetUserUseCase
import com.adikosa.todolistk.domain.usecases.users.GetUserUseCaseImpl
import com.adikosa.todolistk.domain.usecases.users.GetUsersUseCase
import com.adikosa.todolistk.domain.usecases.users.GetUsersUseCaseImpl
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

@Configuration
@EntityScan(basePackages = ["com.adikosa.todolistk.storage"])
@ComponentScan(basePackages = ["com.adikosa.todolistk.storage"])
@EnableJpaRepositories(basePackages = ["com.adikosa.todolistk.storage"])
open class BeansConfiguration {

    @Bean
    open fun provideBase64Encoder(): Base64.Encoder {
        return Base64.getEncoder()
    }

    @Bean
    open fun providePasswordService(): PasswordManager {
        return SpringPasswordManager(10)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun saveTodoUseCaseFactory(todoService: TodoService): SaveTodoUseCase = SaveTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun editTodoUseCaseFactory(todoService: TodoService): EditTodoUseCase = EditTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun getUserTodosUseCaseFactory(todoService: TodoService): GetUserTodosUseCase = GetUserTodosUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun deleteTodoUseCaseFactory(todoService: TodoService): DeleteTodoUseCase = DeleteTodoUseCaseImpl(todoService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun getUserUseCaseFactory(userService: UserService): GetUserUseCase = GetUserUseCaseImpl(userService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun getUsersUseCaseFactory(userService: UserService): GetUsersUseCase = GetUsersUseCaseImpl(userService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun registerUserUseCaseFactory(passwordManager: PasswordManager, jwtTokenManager: JwtTokenManager,
                                        userService: UserService): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(passwordManager, jwtTokenManager, userService)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun loginUserUseCase(jwtTokenManager: JwtTokenManager, userService: UserService,
                              authManager: AuthManager): LoginUserUseCase {
        return LoginUserUseCaseImpl(jwtTokenManager, userService, authManager)
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun deleteUserUseCaseFactory(userService: UserService): DeleteUserUseCase = DeleteUserUseCaseImpl(userService)
}