package com.example.routeandroidtask.repositories

import com.example.routeandroidtask.database.WebServices
import com.example.routeandroidtask.database.model.Product
import com.example.routeandroidtask.repositoriesContract.ProductsRemoteDataSource
import javax.inject.Inject

class ProductsRemoteDataSourceImpl @Inject constructor(private val webServices: WebServices) : ProductsRemoteDataSource {
    override suspend fun getProducts(): List<Product?>? {
        return webServices.getProducts().products
    }
}