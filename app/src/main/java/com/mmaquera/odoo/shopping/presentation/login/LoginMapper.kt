package com.mmaquera.odoo.shopping.presentation.login

fun LoginIntent.mapToAction(): LoginAction {
    return when (this) {
        is LoginIntent.SignIn -> LoginAction.SignIn(user = email, password = password)
    }
}