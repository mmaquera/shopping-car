package com.mmaquera.odoo.shopping.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mmaquera.odoo.shopping.presentation.client.ClientScreen
import com.mmaquera.odoo.shopping.presentation.home.HomeScreen
import com.mmaquera.odoo.shopping.presentation.login.LoginScreen
import com.mmaquera.odoo.shopping.presentation.order.OrderScreen
import com.mmaquera.odoo.shopping.presentation.orderdetail.OrderDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen {
                navController.navigate(Home)
            }
        }

        composable<Home> {
            HomeScreen { clientCode ->
                navController.navigate(Client(id = clientCode))
            }
        }

        composable<Client> { backStackEntry ->
            val client = backStackEntry.toRoute<Client>()
            ClientScreen(clientCode = client.id,
                goToCreateOrder = {
                    navController.navigate(Order)
                }) {
                navController.popBackStack()
            }
        }

        composable<Order> {
            OrderScreen(
                goToDetail = {
                    navController.navigate(OrderDetail)
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable<OrderDetail> {
            OrderDetailScreen()
        }
    }
}