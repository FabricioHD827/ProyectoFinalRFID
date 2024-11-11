package com.example.dni_2

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Define la interfaz de los endpoints de la API
interface ApiService {
    @GET("orders/by-dni")
    suspend fun getOrdersByDni(@Query("dni") dni: Int): List<Order>

    @POST("order/newpriority")
    suspend fun updateOrderToDelivered(@Query("orderId") orderId: String): Response<String>
}

// Define el objeto de Retrofit como singleton fuera de la interfaz
object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
