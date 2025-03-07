package com.mmaquera.odoo.shopping.domain.usecase

import androidx.security.crypto.EncryptedSharedPreferences
import com.mmaquera.odoo.shopping.data.repository.AuthenticateRepository
import com.mmaquera.odoo.shopping.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticateRepository: AuthenticateRepository,
    private val sharedPreferences: EncryptedSharedPreferences
) {

    operator fun invoke(user: String, password: String): Flow<Result<User>> {
        return authenticateRepository.signIn(user = user, password = password)
    }
}