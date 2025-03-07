package com.mmaquera.odoo.shopping.data.request

import ch.qos.logback.core.joran.spi.DefaultClass
import kotlinx.serialization.Contextual
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@ExperimentalSerializationApi
@Serializable
data class ParamsRequest(
    val model: String,
    val method: String,
    @Contextual
    val args: List<JsonElement>,
    @EncodeDefault
    val kwargs: Map<String, JsonElement> = emptyMap()
)