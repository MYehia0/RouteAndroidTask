package com.example.routeandroidtask.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeandroidtask.data.datasources.response.Product
import com.example.routeandroidtask.domain.GetProductsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getProductsInteractor: GetProductsInteractor) : ViewModel() {
    private val _showLoadingLayout = MutableStateFlow(true)
    val showLoadingLayout = _showLoadingLayout.asStateFlow()

    private val _showErrorLayout = MutableStateFlow<String?>(null)
    val showErrorLayout = _showErrorLayout.asStateFlow()

    private val _productsList = MutableStateFlow<List<Product?>?>(null)
    val productsList = _productsList.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _showLoadingLayout.value = true
            try {
                val response = getProductsInteractor()!!
                _productsList.value = response
                _showLoadingLayout.value = false
            } catch (t: HttpException) {
                _showErrorLayout.value = t.localizedMessage
            } catch (ex: Exception) {
                _showLoadingLayout.value = false
                _showErrorLayout.value = ex.localizedMessage
            }
        }
    }
}