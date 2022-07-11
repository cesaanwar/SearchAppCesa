package com.cesa.searchappcesa.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cesa.searchappcesa.R
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.databinding.ActivitySearchBinding
import com.cesa.searchappcesa.di.ViewModelFactory
import com.cesa.searchappcesa.search.products.ProductListFragment
import com.cesa.searchappcesa.search.shops.ShopListFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception
import javax.inject.Inject

class SearchActivity : FragmentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: ActivitySearchBinding

    val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
                    .setContentView(this@SearchActivity, R.layout.activity_search)
        (application as SearchApplication).appComponent.inject(this)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupViewpager()
        setupTabs()
        setupSearchBar()
        viewModel.fetchData("tokopedia")
    }

    private fun setupViewpager() {
        val viewPagerAdapter = SearchFragmentAdapter(this)
        binding.vpLists.adapter = viewPagerAdapter
    }

    private fun setupTabs() {
        TabLayoutMediator(binding.tabLayout, binding.vpLists) { tab, position ->
            var text = ""
            when (position) {
                0 -> text = "Produk"
                1 -> text = "Toko"
                else -> text = "-"
            }
            tab.text = text
        }.attach()
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
        viewModel.fetchData(query)
    }

    private inner class SearchFragmentAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProductListFragment.newInstance()
                1 -> ShopListFragment.newInstance()
                else -> {
                    throw Exception()
                }
            }
        }

    }

}