package com.mmaquera.odoo.shopping.data.request

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class AuthenticateParamsRequest(
    @EncodeDefault
    val db: String = "odoo",
    val login: String,
    val password: String
)