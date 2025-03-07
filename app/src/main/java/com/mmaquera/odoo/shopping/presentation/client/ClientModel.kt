package com.mmaquera.odoo.shopping.presentation.client

import com.mmaquera.odoo.shopping.presentation.Empty

data class ClientModel(
    val name: String = String.Empty,
    val address: String = String.Empty,
    val city: String = String.Empty,
    val documentName: String = String.Empty,
    val documentCode: String = String.Empty,
    val phone: String = String.Empty,
    val mobile: String = String.Empty,
    val email: String = String.Empty
) {
    companion object {
        fun dummy() = ClientModel(
            name = "AVICOLA SANTA GLORIA S.A.C.",
            address = "CAL. PALAZUELOS ESTE MZ.A LT.7 URB. PALAZUELOS ESTE ICA-ICA",
            city = "Lima",
            documentName = "RUC",
            documentCode = "2000000000",
            phone = "987654321",
            mobile = "987654321",
            email = "john.catron@example-pet-store.com"
        )
    }
}