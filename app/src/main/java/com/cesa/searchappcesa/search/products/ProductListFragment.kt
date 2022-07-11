package com.cesa.searchappcesa.search.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesa.searchappcesa.R
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.data.Result
import com.cesa.searchappcesa.databinding.FragmentProductListBinding
import com.cesa.searchappcesa.di.ViewModelFactory
import com.cesa.searchappcesa.search.SearchActivity
import com.cesa.searchappcesa.search.SearchViewModel
import javax.inject.Inject

class ProductListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: SearchViewModel by viewModels({activity as SearchActivity}) { viewModelFactory }

    lateinit var binding: FragmentProductListBinding

    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as SearchApplication).appComponent.inject(this)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupAdapter()
        setupLoadMore()
    }

    private fun setupLoadMore() {
        val layoutManager = binding.rvProducts.layoutManager as LinearLayoutManager
        binding.rvProducts.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val countItem = layoutManager.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (isLastPosition) {
                    viewModel.loadMoreProducts()
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = ProductAdapter(viewModel)
        binding.rvProducts.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}