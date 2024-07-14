package com.example.routeandroidtask.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeandroidtask.data.datasources.response.Product
import com.example.routeandroidtask.domain.GetProductsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getProductsInteractor: GetProductsInteractor) : ViewModel() {
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
        viewModelScope.launch(Dispatchers.IO) {
            _showLoadingLayout.postValue(true)
            try {
                val response = getProductsInteractor()!!
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