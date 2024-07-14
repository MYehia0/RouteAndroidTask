package com.example.routeandroidtask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeandroidtask.database.model.Product
import com.example.routeandroidtask.repositoriesContract.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel() {
    private val _showLoadingLayout = MutableLiveData<Boolean>()
    val showLoadingLayout: LiveData<Boolean>
        get() = _showLoadingLayout

    private val _showErrorLayout = MutableLiveData<String>()
    val showErrorLayout: LiveData<String>
        get() = _showErrorLayout

    private val _productsList = MutableLiveData<List<Product?>>()
    val productsList: LiveData<List<Product?>>
        get() = _productsList

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch {
            _showLoadingLayout.postValue(true)
            try {
                val response = productsRepository.getProducts()!!
                _productsList.postValue(response)
                _showLoadingLayout.postValue(false)
            } catch (t: HttpException) {
                _showErrorLayout.postValue(t.localizedMessage)
            } catch (ex: Exception) {
                _showLoadingLayout.postValue(false)
                _showErrorLayout.postValue(ex.localizedMessage)
            }
        }
    }
}