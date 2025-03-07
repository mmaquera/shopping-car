package com.mmaquera.odoo.shopping.presentation.home

import com.mmaquera.odoo.shopping.domain.model.Client
import com.mmaquera.odoo.shopping.presentation.home.model.ClientModel

fun HomeIntent.mapToAction(): HomeAction {
    return when (this) {
        is HomeIntent.GetClients -> HomeAction.LoadClients
    }
}

fun List<Client>.toPresentation(): List<ClientModel> {
    return this.map {
        ClientModel(
            id = it.id,
            name = it.name,
            ruc = it.documentCode,
            address = it.city
        )
    }
}