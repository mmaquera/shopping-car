package com.mmaquera.odoo.shopping.modules

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context // Contexto de la aplicación
    ): EncryptedSharedPreferences {
        // Crear una clave maestra para cifrar las preferencias
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        // Crear y retornar una instancia de EncryptedSharedPreferences
        return EncryptedSharedPreferences.create(
            context,
            "secure_prefs", // Nombre del archivo de preferencias
            masterKey, // Clave maestra
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // Esquema de cifrado para las claves
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Esquema de cifrado para los valores
        ) as EncryptedSharedPreferences
    }
}