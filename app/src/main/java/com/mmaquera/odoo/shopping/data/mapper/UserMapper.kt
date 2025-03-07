package com.mmaquera.odoo.shopping.data.mapper

import com.mmaquera.odoo.shopping.data.response.ApiResponse
import com.mmaquera.odoo.shopping.data.response.ResultResponse
import com.mmaquera.odoo.shopping.domain.model.User

fun ApiResponse<ResultResponse>.toDomain() = User(
    id = result.userId
)