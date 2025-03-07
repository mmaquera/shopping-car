package com.mmaquera.odoo.shopping.domain.usecase

import com.mmaquera.odoo.shopping.data.repository.ClientRepository
import com.mmaquera.odoo.shopping.domain.model.Client
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(): Flow<Result<List<Client>>> {
        return clientRepository.clients()
    }
}