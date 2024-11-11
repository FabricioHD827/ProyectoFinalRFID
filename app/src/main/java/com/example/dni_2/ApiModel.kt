package com.example.dni_2

data class Order(
    val orderId: String,
    val customerId: String,
    val orderDate: OrderDate,
    val state: String,
    val priority: Int,
    val totalAmount: Double,
    val items: List<OrderItem>
)

data class OrderDate(
    val seconds: Long,
    val nanos: Int
)

data class OrderItem(
    val quantity: Int,
    val price: Double,
    val product_id: String,
    val product_name: String
)
