package com.mmaquera.odoo.shopping.presentation.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmaquera.odoo.shopping.presentation.Empty
import com.mmaquera.odoo.shopping.ui.theme.ShoppingcardTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    goToHome: () -> Unit = {},
) {
    val viewState: LoginViewState by viewModel.viewState.collectAsStateWithLifecycle()

    val (email, onEmailChange) = remember { mutableStateOf(String.Empty) }
    val (password, onPasswordChange) = remember { mutableStateOf(String.Empty) }

    LoginContent(
        email = email,
        onEmailChange = onEmailChange,
        password = password,
        onPasswordChange = onPasswordChange
    ) {
        viewModel.processIntent(LoginIntent.SignIn(email = email, password = password))
    }

    LaunchedEffect(viewState) {
        if (viewState.loginSuccess) {
            goToHome()
        }
    }
}

@Composable
fun LoginContent(
    email: String = String.Empty,
    onEmailChange: (String) -> Unit = {},
    password: String = String.Empty,
    onPasswordChange: (String) -> Unit = {},
    loginOnClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {

        val (shoppingIcon, emailTextField, passwordTextField, loginButton) = createRefs()

        createVerticalChain(
            shoppingIcon,
            emailTextField,
            passwordTextField,
            loginButton,
            chainStyle = ChainStyle.Packed
        )

        Icon(
            modifier = Modifier
                .constrainAs(shoppingIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(emailTextField.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(60.dp),
            imageVector = Icons.Filled.ShoppingCart,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = String.Empty
        )

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(emailTextField) {
                    top.linkTo(shoppingIcon.bottom)
                    bottom.linkTo(passwordTextField.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                }
                .padding(top = 15.dp),
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text("Correo electronico")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(passwordTextField) {
                    top.linkTo(emailTextField.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                }
                .padding(top = 15.dp),
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = onPasswordChange,
            label = {
                Text("Constraseña")
            }
        )

        Button(
            modifier = Modifier
                .constrainAs(loginButton) {
                    top.linkTo(passwordTextField.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 15.dp),
            onClick = loginOnClick
        ) {
            Text("Ingresar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ShoppingcardTheme {
        LoginContent()
    }
}