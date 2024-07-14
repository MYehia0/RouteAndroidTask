package com.example.routeandroidtask.data.datasources

import com.example.routeandroidtask.data.datasources.response.ProductsResponse
import retrofit2.http.GET

interface WebServices {
    @GET("/products")
    suspend fun getProducts(): ProductsResponse
}