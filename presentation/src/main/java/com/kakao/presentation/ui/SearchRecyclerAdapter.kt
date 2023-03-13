package com.kakao.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kakao.presentation.databinding.ViewHolderSearchBinding
import com.kakao.presentation.model.SearchUiModel

class SearchRecyclerAdapter(private val clickListener: (SearchUiModel) -> Unit) : PagingDataAdapter<SearchUiModel, SearchRecyclerAdapter.SearchItemViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SearchUiModel>() {
            override fun areItemsTheSame(oldItem: SearchUiModel, newItem: SearchUiModel): Boolean {
                return oldItem.docUrl == newItem.docUrl
            }
            override fun areContentsTheSame(oldItem: SearchUiModel, newItem: SearchUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding = ViewHolderSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class SearchItemViewHolder(
        private val binding: ViewHolderSearchBinding,
        private val clickListener: (SearchUiModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var model: SearchUiModel? = null

        init {
            binding.root.setOnClickListener {
                if (model == null) return@setOnClickListener
                clickListener.invoke(model!!)
            }
        }

        fun setData(document: SearchUiModel?) {
            if (document == null) return
            this.model = document
            Glide.with(itemView.context).load(document.imageUrl).centerCrop().into(binding.image)
        }

    }

}