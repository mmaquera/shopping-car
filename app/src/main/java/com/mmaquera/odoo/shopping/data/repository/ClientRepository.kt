package com.mmaquera.odoo.shopping.data.repository

import com.mmaquera.odoo.shopping.data.mapper.toDomain
import com.mmaquera.odoo.shopping.data.server.ClientService
import com.mmaquera.odoo.shopping.domain.model.Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientService: ClientService
) {

    fun clients(): Flow<Result<List<Client>>> {
        return flow {
            emit(clientService.clients())
        }.map {
            it.mapCatching { clientList ->
                clientList.toDomain()
            }
        }
    }

    fun client(id: Int): Flow<Result<Client>> {
        return flow {
            emit(clientService.client(id))
        }.map {
            it.mapCatching { client ->
                client.toDomain().first()
            }
        }
    }
}