package com.mmaquera.odoo.shopping.presentation.client

sealed class ClientIntent {
    data class GetClient(val clientCode: Int) : ClientIntent()
}