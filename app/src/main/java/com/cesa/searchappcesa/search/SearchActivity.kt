package com.cesa.searchappcesa.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.cesa.searchappcesa.R
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.databinding.ActivitySearchBinding
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    @Inject
    lateinit var viewModel: SearchViewModel

    lateinit var adapter: ProductAdapter

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
                    .setContentView(this@SearchActivity, R.layout.activity_search)
        (applicationContext as SearchApplication).appComponent.inject(this)
        binding.lifecycleOwner = this
        adapter = ProductAdapter(viewModel)
        binding.rvProducts.adapter = adapter
        binding.viewModel = viewModel
        setupSearchBar()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.errorEvent.observe(this) { isError ->
            if (isError)
                Toast
                    .makeText(this,
                        getString(R.string.search_err_msg),
                        Toast.LENGTH_LONG)
                    .show()
        }
    }

    private fun setupSearchBar() {
        binding.etSearch.addTextChangedListener {
            val query = it.toString()
            handler.removeCallbacksAndMessages(null)

            handler.postDelayed(Runnable {
                enterQuery(query)
            }, 1000)
        }
    }

    private fun enterQuery(query: String) {
        viewModel.enterQuery(query)
    }
}