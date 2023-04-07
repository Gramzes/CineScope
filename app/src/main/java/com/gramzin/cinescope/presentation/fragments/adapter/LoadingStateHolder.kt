package com.gramzin.cinescope.presentation.fragments.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gramzin.cinescope.databinding.LoadingStateHolderBinding

class LoadingStateHolder(private val binding: LoadingStateHolderBinding, retry: () -> Unit)
    : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryBtn.setOnClickListener {retry()}
    }
    fun bind(loadState: LoadState){
        binding.errorMessage.isVisible = loadState is LoadState.Error
        binding.retryBtn.isVisible = loadState is LoadState.Error
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorMessage.text = if (loadState is LoadState.Error) loadState.error.localizedMessage else ""
    }
}