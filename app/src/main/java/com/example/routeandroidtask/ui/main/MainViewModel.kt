package com.example.routeandroidtask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeandroidtask.database.ApiManager
import com.example.routeandroidtask.database.model.Product
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
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
                val response = ApiManager.getApis().getProducts().products!!
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