package com.example.dni_2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OrderDetailScreen(orderId: String?) {
    // Recupera y muestra los detalles de la orden según el orderId
    Text(text = "Detalles de la Orden ID: $orderId")
}
