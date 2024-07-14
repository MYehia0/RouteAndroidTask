package com.example.routeandroidtask.repositoriesContract

import com.example.routeandroidtask.database.model.Product

interface ProductsRepository {
    suspend fun getProducts(): List<Product?>?
}

interface ProductsRemoteDataSource {
    suspend fun getProducts(): List<Product?>?
}
