package com.example.routeandroidtask.database

import com.example.routeandroidtask.database.model.ProductsResponse
import retrofit2.http.GET

interface WebServices {
    @GET("/products")
    suspend fun getProducts(): ProductsResponse
}