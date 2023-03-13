package com.kakao.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kakao.presentation.R
import com.kakao.presentation.databinding.ActivityImageDetailBinding
import com.kakao.presentation.model.SearchUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailActivity : FragmentActivity() {

    companion object {
        fun getIntent(context: Context, model: SearchUiModel): Intent {
            return Intent(context, ImageDetailActivity::class.java).apply {
                putExtra(SearchUiModel::class.java.simpleName, model)
            }
        }
    }

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = ImageDetailFragment.newInstance().apply {
            arguments = intent.extras
        }
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragment, fragment::class.java.simpleName).commit()
    }

}