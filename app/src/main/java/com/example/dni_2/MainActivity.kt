package com.example.dni_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dni_2.ui.theme.DNI_2Theme

class MainActivity : ComponentActivity() {
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Navigation(orderViewModel)
            }
        }
    }
}

@Composable
fun Navigation(orderViewModel: OrderViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "enter_dni") {
        composable("enter_dni") {
            EnterDniScreen { dni ->
                orderViewModel.getOrdersByDni(dni)  // Llama al ViewModel para obtener las órdenes
                navController.navigate("order_list")  // Navega a la lista de órdenes
            }
        }
        composable("order_list") {
            OrderListScreen(orderViewModel) { order ->
                navController.navigate("order_detail/${order.orderId}")
            }
        }
        composable("order_detail/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")
            OrderDetailScreen(orderId)
        }
    }
}
