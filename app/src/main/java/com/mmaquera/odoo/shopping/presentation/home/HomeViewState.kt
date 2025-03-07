package com.mmaquera.odoo.shopping.presentation.home

import com.mmaquera.odoo.shopping.presentation.home.model.ClientModel

data class HomeViewState(
    val clients: List<ClientModel> = emptyList()
)