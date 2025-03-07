package com.mmaquera.odoo.shopping.presentation.client

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmaquera.odoo.shopping.ui.theme.ShoppingcardTheme


@Composable
fun ClientScreen(
    viewModel: ClientViewModel = hiltViewModel(),
    clientCode: Int,
    onBackPressed: () -> Unit = {}
) {

    val viewState: ClientViewState by viewModel.viewState.collectAsStateWithLifecycle()

    ClientContent(
        client = viewState.client,
        onBackPressed = onBackPressed
    )

    LaunchedEffect(Unit) {
        viewModel.processIntent(ClientIntent.GetClient(clientCode = clientCode))
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientContent(
    client: ClientModel = ClientModel.dummy(),
    onBackPressed: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(text = client.name)
                }
            )
        }
    ) { innerPadding ->
        ConstraintLayout(modifier = Modifier.padding(innerPadding)) {

            val (nameLabel, name,
                addressLabel, address,
                cityLabel, city,
                documentName, documentCode,
                phoneLabel, phone,
                mobileLabel, mobile,
                emailLabel, email) = createRefs()

            Text(
                modifier = Modifier.constrainAs(nameLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                text = "Nombre:"
            )
            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(nameLabel.bottom)
                    start.linkTo(parent.start)
                },
                text = client.name
            )
            Text(
                modifier = Modifier.constrainAs(addressLabel) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                },
                text = "Dirección:"
            )
            Text(
                modifier = Modifier.constrainAs(address) {
                    top.linkTo(addressLabel.bottom)
                    start.linkTo(parent.start)
                },
                text = client.address
            )
            Text(
                modifier = Modifier.constrainAs(cityLabel) {
                    top.linkTo(address.bottom)
                    start.linkTo(parent.start)
                },
                text = "Ciudad:"
            )
            Text(
                modifier = Modifier.constrainAs(city) {
                    top.linkTo(address.bottom)
                    start.linkTo(cityLabel.end)
                },
                text = client.city
            )
            Text(
                modifier = Modifier.constrainAs(documentName) {
                    top.linkTo(city.bottom)
                    start.linkTo(parent.start)
                },
                text = "${client.documentName}:"
            )
            Text(
                modifier = Modifier.constrainAs(documentCode) {
                    top.linkTo(city.bottom)
                    start.linkTo(documentName.end)
                },
                text = client.documentCode
            )
            Text(
                modifier = Modifier.constrainAs(phoneLabel) {
                    top.linkTo(documentCode.bottom)
                    start.linkTo(parent.start)
                },
                text = "Teléfono:"
            )
            Text(
                modifier = Modifier.constrainAs(phone) {
                    top.linkTo(documentCode.bottom)
                    start.linkTo(phoneLabel.end)
                },
                text = client.phone
            )
            Text(
                modifier = Modifier.constrainAs(mobileLabel) {
                    top.linkTo(phoneLabel.bottom)
                    start.linkTo(parent.start)
                },
                text = "Celular:"
            )
            Text(modifier = Modifier.constrainAs(mobile) {
                top.linkTo(phoneLabel.bottom)
                start.linkTo(mobileLabel.end)
            }, text = client.mobile)
            Text(
                modifier = Modifier.constrainAs(emailLabel) {
                    top.linkTo(mobileLabel.bottom)
                    start.linkTo(parent.start)
                },
                text = "Email:"
            )
            Text(modifier = Modifier.constrainAs(email) {
                top.linkTo(mobileLabel.bottom)
                start.linkTo(emailLabel.end)
            }, text = client.email)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ClientScreenPreview() {
    ShoppingcardTheme {
        ClientContent()
    }
}