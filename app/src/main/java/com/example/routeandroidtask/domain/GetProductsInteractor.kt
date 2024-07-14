package com.example.routeandroidtask.domain

import com.example.routeandroidtask.data.repositories.ProductsRepository
import javax.inject.Inject

class GetProductsInteractor @Inject constructor(private val productsRepository: ProductsRepository){
    suspend operator fun invoke() = productsRepository.getProducts()
}