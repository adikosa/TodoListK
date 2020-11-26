package com.adikosa.todolistk.network.googleapi

import com.adikosa.todolistk.network.model.GoogleTasksListsResponse
import org.springframework.web.client.RestTemplate

interface GoogleApiRepository {

}

class GoogleApiRepositoryImpl(
        private val restTemplate: RestTemplate
): GoogleApiRepository {
//    fun hasTaskList(title: String): Boolean {
//
//    }
//
//    fun getTasksLists(): GoogleTasksListsResponse {
//
//    }
}