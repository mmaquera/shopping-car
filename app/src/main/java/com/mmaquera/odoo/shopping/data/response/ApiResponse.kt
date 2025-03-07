package com.mmaquera.odoo.shopping.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val jsonrpc: String,
    val result: T
)
