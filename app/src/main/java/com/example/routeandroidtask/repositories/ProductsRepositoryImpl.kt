package com.example.routeandroidtask.repositories

import com.example.routeandroidtask.database.model.Product
import com.example.routeandroidtask.repositoriesContract.ProductsRemoteDataSource
import com.example.routeandroidtask.repositoriesContract.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsRemoteDataSource: ProductsRemoteDataSource) : ProductsRepository {
    override suspend fun getProducts(): List<Product?>? {
        return productsRemoteDataSource.getProducts()
    }
}