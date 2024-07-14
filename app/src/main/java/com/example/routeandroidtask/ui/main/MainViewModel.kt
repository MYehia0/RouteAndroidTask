package com.example.routeandroidtask.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeandroidtask.database.ApiManager
import com.example.routeandroidtask.database.model.Product
import com.example.routeandroidtask.database.model.ProductsResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

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
        _showLoadingLayout.value = true
        ApiManager.getApis().getProducts().enqueue(object :Callback<ProductsResponse>{
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                _showLoadingLayout.value = false
                if (response.isSuccessful) {
                    _productsList.value = response.body()?.products!!
                }
                else {
                    _showErrorLayout.value = "Response is not successful"
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                _showLoadingLayout.value = false
                _showErrorLayout.value = t.localizedMessage
            }
        })
    }
}