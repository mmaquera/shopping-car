package com.mmaquera.odoo.shopping.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("uid")
    val userId: Int,
    @SerialName("server_version")
    val erpVersion: String,
    val name: String,
    val username: String,
    @SerialName("user_companies")
    val companies: UserCompanies
)

@Serializable
data class UserCompanies(
    @SerialName("allowed_companies")
    val allowedCompanies: HashMap<String, Company>
)

@Serializable
data class Company(
    val id: Int,
    val name: String
)