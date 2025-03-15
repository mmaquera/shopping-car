package com.mmaquera.odoo.shopping.presentation.orderdetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mmaquera.odoo.shopping.presentation.Empty
import com.mmaquera.odoo.shopping.ui.theme.ShoppingcardTheme

@Composable
fun OrderDetailScreen() {
    OrderDetailContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailContent() {
    val (searchText, onSearchTextChange) = remember { mutableStateOf(String.Empty) }
    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = onSearchTextChange,
                placeholder = { Text("Buscar") },
                onSearch = {},
                active = false,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {

            }
        }
    ) { innerPadding ->
        ConstraintLayout(modifier = Modifier.padding(innerPadding)) {
            ItemProduct()
        }
    }
}

@Preview
@Composable
fun ItemProduct(
    product: ProductModel = ProductModel.dummy(),
    isAdd: Boolean = false
) {
    val (quantity, onQuantityChange) = remember { mutableStateOf("1") }
    Card {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val (name, price, upOrDownQuantity, delete, addButton) = createRefs()

            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(price.start)
                    width = Dimension.fillToConstraints
                },
                text = product.name
            )
            Text(
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(parent.top)
                    start.linkTo(name.end)
                    end.linkTo(parent.end)
                },
                text = "S/ ${product.price}"
            )

            if (isAdd) {
                TextButton(
                    modifier = Modifier.constrainAs(delete) {
                        top.linkTo(upOrDownQuantity.top)
                        bottom.linkTo(upOrDownQuantity.bottom)
                    },
                    onClick = {}
                ) {
                    Text(
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        ),
                        text = "Eliminar"
                    )
                }

                UpOrDownQuantity(
                    modifier = Modifier.constrainAs(upOrDownQuantity) {
                        top.linkTo(price.bottom)
                        end.linkTo(parent.end)
                    },
                    quantity = quantity,
                    onQuantityChange = onQuantityChange
                )
            } else {
                IconButton(
                    modifier = Modifier.constrainAs(addButton) {
                        top.linkTo(price.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Default.AddCircle,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = String.Empty
                    )
                }

            }

        }
    }
}

@Preview
@Composable
private fun UpOrDownQuantity(
    modifier: Modifier = Modifier,
    quantity: String = "1",
    onQuantityChange: (String) -> Unit = {}
) {
    Card(modifier = modifier) {
        ConstraintLayout {
            val (down, input, up) = createRefs()
            IconButton(
                modifier = Modifier.constrainAs(down) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(input.start)
                    bottom.linkTo(parent.bottom)
                },
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = String.Empty
                )
            }

            TextField(
                modifier = Modifier
                    .constrainAs(input) {
                        top.linkTo(parent.top)
                        start.linkTo(down.end)
                        end.linkTo(up.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(40.dp),
                value = quantity,
                onValueChange = onQuantityChange,
            )

            IconButton(
                modifier = Modifier.constrainAs(up) {
                    top.linkTo(parent.top)
                    start.linkTo(input.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = String.Empty
                )
            }
        }
    }
}

data class ProductModel(
    val name: String,
    val price: Double,
    val quantity: Int,
    val stock: Int
) {
    companion object {
        fun dummy() = ProductModel(
            name = "Producto 1",
            price = 100.0,
            quantity = 1,
            stock = 10
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun OrderDetailPreview() {
    ShoppingcardTheme {
        OrderDetailContent()
    }
}

