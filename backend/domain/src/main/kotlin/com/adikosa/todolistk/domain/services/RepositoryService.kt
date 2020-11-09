package com.adikosa.todolistk.domain.services

interface RepositoryService<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T
    fun save(t: T): T
    fun save(list: List<T>): List<T>
    fun existsById(id: ID): Boolean
    fun deleteById(id: ID)
    fun update(t: T, id: ID): T
}