package com.mmaquera.odoo.shopping.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmaquera.odoo.shopping.presentation.home.model.ClientModel
import com.mmaquera.odoo.shopping.ui.theme.ShoppingcardTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    goToClient: (Int) -> Unit = {}
) {
    val viewState: HomeViewState by viewModel.viewState.collectAsStateWithLifecycle()

    HomeContent(
        clientList = viewState.clients
    ) {
        goToClient(it)
    }

    LaunchedEffect(Unit) {
        viewModel.processIntent(intent = HomeIntent.GetClients)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    clientList: List<ClientModel> = emptyList(),
    goToClientDetail: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Clientes")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items(clientList) { item ->
                ClientCard(clientModel = item) {
                    goToClientDetail(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun ClientCard(
    clientModel: ClientModel = ClientModel(
        id = 1,
        name = "BODEGA LA GATA",
        ruc = "2000000201201",
        address = "Lima"
    ),
    goToClient: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clickable {
                goToClient.invoke(clientModel.id)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(10.dp)
        ) {
            val (name, document, address) = createRefs()

            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }, text = clientModel.name
            )

            Text(modifier = Modifier.constrainAs(document) {
                top.linkTo(name.bottom)
                start.linkTo(parent.start)
            }, text = clientModel.ruc)

            Text(
                modifier = Modifier
                    .constrainAs(address) {
                        top.linkTo(document.bottom)
                        start.linkTo(parent.start)
                    }, text = clientModel.address
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ShoppingcardTheme {
        HomeContent()
    }
}