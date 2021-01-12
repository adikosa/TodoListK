package com.adikosa.todolistk.network.configuration

import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class AuthorizationHeaderInterceptor(
        private val token: String
) : ClientHttpRequestInterceptor {
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        request.headers.set("Authorization", "Bearer $token")
        return execution.execute(request, body);
    }
}