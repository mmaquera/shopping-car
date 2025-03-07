package com.mmaquera.odoo.shopping.domain.model

data class Client(
    val id: Int,
    val name: String,
    val address: String,
    val email: String,
    val phone: String,
    val mobile: String,
    val city: String,
    val documentName: String,
    val documentCode: String,
)