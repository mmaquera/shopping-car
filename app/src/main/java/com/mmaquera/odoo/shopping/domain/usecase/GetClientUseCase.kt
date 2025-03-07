package com.mmaquera.odoo.shopping.domain.usecase

import com.mmaquera.odoo.shopping.data.repository.ClientRepository
import com.mmaquera.odoo.shopping.domain.model.Client
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(clientCode: Int): Flow<Result<Client>> {
        return clientRepository.client(id = clientCode)
    }
}