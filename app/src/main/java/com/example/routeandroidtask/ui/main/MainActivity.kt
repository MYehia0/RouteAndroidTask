package com.example.routeandroidtask.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.routeandroidtask.R
import com.example.routeandroidtask.database.model.Product
import com.example.routeandroidtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ProductAdapter
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = ProductAdapter(null)
        binding.content.productRecycler.adapter = adapter
        binding.content.tryAgain.setOnClickListener {
            viewModel.loadProducts()
        }
        subscribeToLiveData()
//        viewModel.loadProducts()
    }

    private fun subscribeToLiveData() {
        viewModel.showLoadingLayout.observe(this) {
            showLoadingLayout(it)
            if (it) {
                hideErrorLayout()
            }
        }
        viewModel.showErrorLayout.observe(this) {
            showErrorLayout(it)
        }
        viewModel.productsList.observe(this) {
            adapter.changeData(it)
        }
    }

    private fun showErrorLayout(message: String?) {
        binding.content.errorLayout.isVisible = true
        binding.content.errorMessage.text = message
        binding.content.productRecycler.isVisible = false
    }

    private fun showLoadingLayout(flag: Boolean) {
        binding.content.loadingIndicator.isVisible = flag
    }

    private fun hideErrorLayout() {
        binding.content.errorLayout.isVisible = false
        binding.content.productRecycler.isVisible = true
    }

}