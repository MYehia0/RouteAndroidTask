package com.example.routeandroidtask.ui.products

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.routeandroidtask.R
import com.example.routeandroidtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = ProductAdapter(null)
        binding.content.productRecycler.adapter = adapter
        binding.content.tryAgain.setOnClickListener {
            viewModel.loadProducts()
        }
        handleScreenState()
    }

    private fun handleScreenState() {
        lifecycleScope.launch {
            launch {
                viewModel.showLoadingLayout.collect{
                    showLoadingLayout(it)
                    if (it) {
                        hideErrorLayout()
                    }
                }
            }
            launch {
                viewModel.showErrorLayout.collect {
                    it?.let { showErrorLayout(it) }
                }
            }
            launch {
                viewModel.productsList.collect {
                    it?.let { adapter.changeData(it) }
                }
            }
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