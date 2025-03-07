package com.mmaquera.odoo.shopping.modules.networking

import androidx.security.crypto.EncryptedSharedPreferences
import com.mmaquera.odoo.shopping.presentation.Empty
import com.mmaquera.odoo.shopping.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @BaseUrl baseUrl: String,
        encryptedSharedPreferences: EncryptedSharedPreferences
    ): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // Ignora claves desconocidas en la respuesta JSON
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            // install(HttpRequestInterceptor)

            HttpResponseValidator {
                validateResponse { response ->
                    val setCookieHeader = response.headers[HttpHeaders.SetCookie]

                    val session = setCookieHeader
                        ?.split(";")
                        ?.find { it.startsWith("session_id=") }
                        ?.split("=")
                        ?.get(1)

                    if (session != null) {
                        with(encryptedSharedPreferences.edit()) {
                            putString("session_id", session)
                            apply()

                        }
                    }
                }
            }

            defaultRequest {
                url(baseUrl)
                header(HttpHeaders.Cookie, "session_id=${encryptedSharedPreferences.getString("session_id", String.Empty)}")
                contentType(ContentType.Application.Json)
            }
        }
    }
}