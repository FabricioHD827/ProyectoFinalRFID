package com.example.dni_2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    // Función para obtener órdenes por DNI
    fun getOrdersByDni(dni: Int) {
        viewModelScope.launch {
            try {
                _orders.value = RetrofitInstance.api.getOrdersByDni(dni)
            } catch (e: Exception) {
                Log.e("OrderViewModel", "Error fetching orders", e)
            }
        }
    }

    // Función para actualizar el estado de la orden a "Entregado"
    fun updateOrderToDelivered(orderId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.updateOrderToDelivered(orderId)
                if (response.isSuccessful) {
                    onResult(true)
                    // Refresca la lista de órdenes para reflejar los cambios si es necesario
                    _orders.value = _orders.value?.map { order ->
                        if (order.orderId == orderId) order.copy(state = "Entregado") else order
                    }
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("OrderViewModel", "Error updating order status", e)
                onResult(false)
            }
        }
    }
}
