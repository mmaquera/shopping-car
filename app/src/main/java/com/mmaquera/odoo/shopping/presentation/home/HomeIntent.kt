package com.mmaquera.odoo.shopping.presentation.home

sealed class HomeIntent {
    data object GetClients : HomeIntent()
}