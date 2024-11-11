package com.example.dni_2


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EnterDniScreen(onDniEntered: (Int) -> Unit) {
    var dniInput by remember { mutableStateOf("") }
    val isDniValid = dniInput.isNotEmpty() && dniInput.all { it.isDigit() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ingrese su DNI", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = dniInput,
            onValueChange = { dniInput = it },
            label = { Text("DNI") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onDniEntered(dniInput.toInt()) },
            enabled = isDniValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar Órdenes")
        }
    }
}
