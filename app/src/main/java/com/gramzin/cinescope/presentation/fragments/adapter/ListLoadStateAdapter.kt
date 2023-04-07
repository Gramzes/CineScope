package com.gramzin.cinescope.presentation.fragments.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.gramzin.cinescope.databinding.LoadingStateHolderBinding

class ListLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateHolder>() {
    override fun onBindViewHolder(holder: LoadingStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingStateHolder {
        Log.d("alexkeks", "create")
        val binding = LoadingStateHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateHolder(binding, retry)
    }
}