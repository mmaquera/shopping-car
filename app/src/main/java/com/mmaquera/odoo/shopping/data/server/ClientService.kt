package com.mmaquera.odoo.shopping.data.server

import com.mmaquera.odoo.shopping.data.request.JsonRpcRequest
import com.mmaquera.odoo.shopping.data.request.ParamsRequest
import com.mmaquera.odoo.shopping.data.response.ApiResponse
import com.mmaquera.odoo.shopping.data.response.ClientResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class ClientService @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun clients(): Result<ApiResponse<List<ClientResponse>>> {
        return runCatching {
            val body = JsonRpcRequest(
                params = ParamsRequest(
                    model = "res.partner",
                    method = "search_read",
                    args = listOf(
                        buildJsonArray {
                            add(buildJsonArray {
                                add(JsonPrimitive("customer_rank"))
                                add(JsonPrimitive(">"))
                                add(JsonPrimitive(0))
                            })
                            add(buildJsonArray {
                                add(JsonPrimitive("vat"))
                                add(JsonPrimitive("!="))
                                add(JsonPrimitive(""))
                            })
                        },
                        buildJsonArray {
                            add(JsonPrimitive("name"))
                            add(JsonPrimitive("email"))
                            add(JsonPrimitive("phone"))
                            add(JsonPrimitive("vat"))
                            add(JsonPrimitive("company_id"))
                        }, // Campos a recuperar
                        JsonPrimitive(0), // Offset
                        JsonPrimitive(0), // Límite
                        JsonPrimitive("id asc"), // Orden
                    )
                )
            )

            val response: HttpResponse = httpClient.post("/web/dataset/call_kw/") {
                setBody(body)
            }

            return Result.success(response.body())
        }.onFailure {
            return Result.failure(it)
        }
    }

    suspend fun client(id: Int): Result<ApiResponse<List<ClientResponse>>> {
        return runCatching {
            val body = JsonRpcRequest(
                params = ParamsRequest(
                    model = "res.partner",
                    method = "search_read",
                    args = emptyList(),
                    kwargs = mapOf(
                        "domain" to buildJsonArray {
                            add(buildJsonArray {
                                add(JsonPrimitive("id"))
                                add(JsonPrimitive("="))
                                add(JsonPrimitive(id))
                            })
                        },
                        "fields" to buildJsonArray {
                            add(JsonPrimitive("name"))
                            add(JsonPrimitive("street"))
                            add(JsonPrimitive("email"))
                            add(JsonPrimitive("phone"))
                            add(JsonPrimitive("mobile"))
                            add(JsonPrimitive("city"))
                            add(JsonPrimitive("vat_label"))
                            add(JsonPrimitive("vat"))
                        },
                    )
                )
            )
            val response: HttpResponse = httpClient.post("/web/dataset/call_kw/") {
                setBody(body)
            }
            return Result.success(response.body())
        }.onFailure {
            return Result.failure(it)
        }
    }
}