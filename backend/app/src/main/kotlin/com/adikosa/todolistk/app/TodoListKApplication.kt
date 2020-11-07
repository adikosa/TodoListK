package com.adikosa.todolistk.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TodoListKApplication

fun main(args: Array<String>) {
    runApplication<TodoListKApplication>(*args)
}