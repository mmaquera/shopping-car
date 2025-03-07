package com.mmaquera.odoo.shopping.presentation.client

import com.mmaquera.odoo.shopping.domain.model.Client

fun ClientIntent.mapToAction(): ClientAction {
    return when (this) {
        is ClientIntent.GetClient -> ClientAction.GetClient(clientCode = clientCode)
    }
}

fun Client.toPresentation(): ClientModel {
    return ClientModel(
        name = name,
        address = address,
        city = city,
        documentName = documentName,
        documentCode = documentCode,
        phone = phone,
        mobile = mobile
    )
}