package com.kakao.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kakao.presentation.databinding.FragmentImageDeatilBinding
import com.kakao.presentation.viewmodel.ImageDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ImageDetailFragment()
    }

    private lateinit var binding: FragmentImageDeatilBinding
    private val viewModel: ImageDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentImageDeatilBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.searchUiModel.collectLatest {
                if (it.imageUrl.isEmpty()) return@collectLatest
                Glide.with(requireContext()).load(it.imageUrl).into(binding.image)
                binding.siteName.isVisible = it.displaySiteName.isNotEmpty()
                binding.siteName.text = it.displaySiteName
                binding.date.isVisible = it.dateTime.isNotEmpty()
                binding.date.text = it.dateTime
            }
        }
    }

}