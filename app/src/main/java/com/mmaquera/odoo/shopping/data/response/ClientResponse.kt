package com.mmaquera.odoo.shopping.data.response

import com.mmaquera.odoo.shopping.data.serialization.StringOrBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientResponse(
    val id: Int,
    val name: String,
    val street: StringOrBoolean,
    val email: StringOrBoolean,
    val phone: StringOrBoolean,
    val mobile: StringOrBoolean,
    val city: StringOrBoolean,
    @SerialName("vat_label")
    val documentName: StringOrBoolean,
    @SerialName("vat")
    val documentCode: StringOrBoolean
)