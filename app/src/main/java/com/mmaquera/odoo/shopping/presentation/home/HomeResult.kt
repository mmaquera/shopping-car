package com.mmaquera.odoo.shopping.presentation.home

import com.mmaquera.odoo.shopping.domain.model.Client

data class HomeResult(
    val clients: List<Client>
)