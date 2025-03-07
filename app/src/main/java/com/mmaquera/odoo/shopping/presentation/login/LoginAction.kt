package com.mmaquera.odoo.shopping.presentation.login

sealed class LoginAction {

    data class SignIn(val user: String, val password: String) : LoginAction()
}