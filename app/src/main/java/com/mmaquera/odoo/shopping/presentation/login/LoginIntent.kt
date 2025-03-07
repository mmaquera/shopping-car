package com.mmaquera.odoo.shopping.presentation.login

sealed class LoginIntent {
    data class SignIn(val email: String, val password: String) : LoginIntent()
}