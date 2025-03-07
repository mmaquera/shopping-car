package com.mmaquera.odoo.shopping.presentation.client

sealed class ClientAction {
    data class GetClient(val clientCode: Int) : ClientAction()
}