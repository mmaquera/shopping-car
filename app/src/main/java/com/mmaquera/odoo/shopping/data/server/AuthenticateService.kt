package com.mmaquera.odoo.shopping.data.server

import com.mmaquera.odoo.shopping.data.request.AuthenticateParamsRequest
import com.mmaquera.odoo.shopping.data.request.JsonRpcRequest
import com.mmaquera.odoo.shopping.data.response.ApiResponse
import com.mmaquera.odoo.shopping.data.response.ResultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class AuthenticateService @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun authenticate(user: String, password: String): Result<ApiResponse<ResultResponse>> {
        return runCatching {
            val body = JsonRpcRequest(
                params = AuthenticateParamsRequest(
                    login = user,
                    password = password
                )
            )

            val response: HttpResponse = httpClient.post("/web/session/authenticate/") {
                setBody(body)
            }

            val session = response.headers["Set-Cookie"]
                ?.split(";")
                ?.find { it.startsWith("session_id=") }
                ?.split("=")
                ?.get(1)

            return Result.success(response.body())
        }.onFailure {
            return Result.failure(it)
        }
    }
}