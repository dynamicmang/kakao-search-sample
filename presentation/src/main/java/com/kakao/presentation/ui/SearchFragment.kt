package com.kakao.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.kakao.presentation.R
import com.kakao.presentation.databinding.FragmentSearchBinding
import com.kakao.presentation.hideKeyboard
import com.kakao.presentation.model.UiState
import com.kakao.presentation.toFlow
import com.kakao.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val recyclerAdapter = SearchRecyclerAdapter {
        val intent = ImageDetailActivity.getIntent(requireContext(), it)
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        observeData()
    }

    private fun setView() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            val keyword = binding.searchEditText.text
            if (keyword.isEmpty()) return@setOnRefreshListener
            viewModel.search(keyword.toString())
        }
        binding.recyclerView.let {
            it.adapter = recyclerAdapter
            it.setHasFixedSize(true)
            it.addItemDecoration(GridSpacingItemDecoration(3, 3))
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            launch {
                binding.searchEditText.toFlow()
                    .debounce(2000)
                    .collect {
                        val keyword = it.toString()
                        if(keyword.isEmpty()) return@collect
                        binding.searchEditText.hideKeyboard()
                        viewModel.search(keyword)
                    }
            }
            launch {
                viewModel.uiState.collectLatest {
                    when (it) {
                        is UiState.None -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                        }
                        is UiState.Loading -> {
                            binding.swipeRefreshLayout.isRefreshing = true
                        }
                        is UiState.Success -> {
                            isVisibleContent(true)
                            recyclerAdapter.submitData(it.data)
                        }
                        is UiState.Error -> {
                            isVisibleContent(false, it.error.message ?: "")
                            val keyword = binding.searchEditText.text
                            if (keyword.isEmpty()) return@collectLatest
                            viewModel.search(keyword.toString())
                        }
                    }
                }
            }
            launch {
                recyclerAdapter.loadStateFlow.map { it.refresh }
                    .distinctUntilChanged()
                    .collect {
                        if (it is LoadState.NotLoading) {
                            isVisibleContent(recyclerAdapter.itemCount > 0, resources.getString(R.string.empty_search_message))
                        }
                    }
            }
        }
    }

    private fun isVisibleContent(isVisible: Boolean, message: String = "") {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout.isVisible = isVisible
        binding.message.isVisible = isVisible.not()
        binding.message.text = message
    }

}