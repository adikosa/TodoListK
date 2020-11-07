package com.adikosa.todolistk.app.di

import com.adikosa.todolistk.domain.TodoService
import com.adikosa.todolistk.domain.UserService
import com.adikosa.todolistk.domain.usecases.todos.*
import com.adikosa.todolistk.domain.usecases.users.*
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
    open fun registerUserUseCaseFactory(userService: UserService): RegisterUserUseCase = RegisterUserUseCaseImpl(userService)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    open fun deleteUserUseCaseFactory(userService: UserService): DeleteUserUseCase = DeleteUserUseCaseImpl(userService)
}