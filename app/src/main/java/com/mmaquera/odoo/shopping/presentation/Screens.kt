package com.mmaquera.odoo.shopping.presentation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Home

@Serializable
data class Client(val id: Int)