package com.example.routeandroidtask.repositories

import com.example.routeandroidtask.repositoriesContract.ProductsRemoteDataSource
import com.example.routeandroidtask.repositoriesContract.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductsDataSourceModule {
    @Binds
    abstract fun bindProductsDataSource(
        productsDataSource: ProductsRemoteDataSourceImpl
    ): ProductsRemoteDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductsRepositoryModule {
    @Binds
    abstract fun getProductsRepository(
        productsRepository: ProductsRepositoryImpl
    ): ProductsRepository
}
