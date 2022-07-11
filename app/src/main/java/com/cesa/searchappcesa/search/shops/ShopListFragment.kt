package com.cesa.searchappcesa.search.shops

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesa.searchappcesa.R
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.data.Result
import com.cesa.searchappcesa.databinding.FragmentShopListBinding
import com.cesa.searchappcesa.di.ViewModelFactory
import com.cesa.searchappcesa.search.SearchActivity
import com.cesa.searchappcesa.search.SearchViewModel
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: SearchViewModel by viewModels({activity as SearchActivity }) { viewModelFactory }

    lateinit var binding: FragmentShopListBinding

    lateinit var adapter: ShopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShopListBinding.inflate(inflater, container, false)
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
        val layoutManager = binding.rvShops.layoutManager as LinearLayoutManager
        binding.rvShops.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val countItem = layoutManager.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (isLastPosition) {
                    viewModel.loadMoreShops()
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = ShopsAdapter()
        binding.rvShops.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShopListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}