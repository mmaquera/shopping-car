package com.mmaquera.odoo.shopping.data.mapper

import com.mmaquera.odoo.shopping.data.response.ApiResponse
import com.mmaquera.odoo.shopping.data.response.ClientResponse
import com.mmaquera.odoo.shopping.domain.model.Client

fun ApiResponse<List<ClientResponse>>.toDomain(): List<Client> {
    return this.result.map {
        Client(
            name = it.name,
            address = it.street.value,
            email = it.email.value,
            phone = it.phone.value,
            documentCode = it.documentCode.value,
            documentName = it.documentName.value,
            mobile = it.mobile.value,
            city = it.city.value,
            id = it.id
        )
    }
}