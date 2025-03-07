package com.mmaquera.odoo.shopping.data.request

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class JsonRpcRequest<T>(
    @EncodeDefault
    val jsonrpc: String = "2.0",
    @EncodeDefault
    val method: String = "call",
    val params: T
)