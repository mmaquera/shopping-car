package com.mmaquera.odoo.shopping.data.repository

import com.mmaquera.odoo.shopping.data.mapper.toDomain
import com.mmaquera.odoo.shopping.data.server.AuthenticateService
import com.mmaquera.odoo.shopping.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticateRepository @Inject constructor(
    private val authenticateService: AuthenticateService
) {

    fun signIn(user: String, password: String): Flow<Result<User>> {
        return flow {
            emit(authenticateService.authenticate(user = user, password = password))
        }.map { it.mapCatching { user -> user.toDomain() } }
    }
}