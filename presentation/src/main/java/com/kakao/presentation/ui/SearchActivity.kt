package com.kakao.presentation.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kakao.presentation.R
import com.kakao.presentation.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : FragmentActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, SearchFragment.newInstance(), SearchFragment::class.java.simpleName).commit()
    }

}