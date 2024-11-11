package com.example.dni_2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dni_2.Order
import com.example.dni_2.OrderViewModel

@Composable
fun OrderListScreen(orderViewModel: OrderViewModel, onNavigateToOrderDetail: (Order) -> Unit) {
    val orders by orderViewModel.orders.observeAsState(emptyList())

    LazyColumn {
        items(orders) { order ->
            // Pasamos orderViewModel como parámetro a OrderItemView
            OrderItemView(order, orderViewModel, onNavigateToOrderDetail)
        }
    }
}

@Composable
fun OrderItemView(order: Order, orderViewModel: OrderViewModel, onNavigateToOrderDetail: (Order) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNavigateToOrderDetail(order) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Order ID: ${order.orderId}", style = MaterialTheme.typography.h6)
            Text(text = "Status: ${order.state}")
            Text(text = "Total: $${order.totalAmount}")

            Spacer(modifier = Modifier.height(8.dp))

            // Botón "Recoger"
            Button(
                onClick = {
                    orderViewModel.updateOrderToDelivered(order.orderId) { success ->
                        if (success) {

                            // Puedes actualizar el estado local o mostrar un mensaje de éxito si quieres.
                        } else {
                            // Manejar error si es necesario
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Recoger")
            }
        }
    }
}
