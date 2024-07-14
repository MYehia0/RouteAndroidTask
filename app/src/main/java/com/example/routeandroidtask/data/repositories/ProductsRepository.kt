package com.example.routeandroidtask.data.repositories

import com.example.routeandroidtask.data.datasources.WebServices
import com.example.routeandroidtask.data.datasources.response.Product
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val webServices: WebServices) {
    suspend fun getProducts(): List<Product?>? {
        return webServices.getProducts().products
    }
}