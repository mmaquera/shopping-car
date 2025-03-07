package com.mmaquera.odoo.shopping.presentation.home

sealed class HomeAction {

    data object LoadClients: HomeAction()
}