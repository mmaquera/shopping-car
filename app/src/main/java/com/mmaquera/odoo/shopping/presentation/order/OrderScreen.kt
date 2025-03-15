package com.mmaquera.odoo.shopping.presentation.order

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mmaquera.odoo.shopping.ui.theme.ShoppingcardTheme
import java.util.Calendar

@Composable
fun OrderScreen(
    onBackPressed: () -> Unit = {},
    goToDetail: () -> Unit = {}
) {
    OrderContent(onBackPressed = onBackPressed, goToDetail = goToDetail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderContent(
    onBackPressed: () -> Unit = {},
    goToDetail: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = goToDetail
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Crear Pedido"
                )
            }
        },
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
                    Text(text = "Creación de pedido")
                }
            )
        }
    ) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier.padding(innerPadding)
        ) {

            val (clientLabel, clientName, date) = createRefs()

            Text(
                modifier = Modifier.constrainAs(clientLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                text = "Solicitante:"
            )
            Text(
                modifier = Modifier.constrainAs(clientName) {
                    top.linkTo(clientLabel.bottom)
                    start.linkTo(parent.start)
                },
                text = "Nombre del cliente"
            )

            DatePickerWithTextField(
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(clientName.bottom)
                    start.linkTo(parent.start)
                }
            )

        }
    }
}

@Composable
fun DatePickerWithTextField(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    // Mostrar el DatePickerDialog
    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                showDatePicker = false
            },
            year,
            month,
            day
        ).show()
    }

    Column(modifier = modifier.padding(16.dp)) {
        // TextField para mostrar la fecha seleccionada
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            label = { Text("Seleccionar fecha") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,  // Hacer el TextField de solo lectura
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
                }
            }
        )
    }
}

@Preview
@Composable
fun OrderPreview() {
    ShoppingcardTheme {
        OrderContent()
    }
}