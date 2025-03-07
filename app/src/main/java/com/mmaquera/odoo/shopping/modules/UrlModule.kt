package com.mmaquera.odoo.shopping.modules

import com.mmaquera.odoo.shopping.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UrlModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "http://18.220.122.48/"
    }
}