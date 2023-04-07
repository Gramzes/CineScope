package com.gramzin.cinescope.presentation.fragments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gramzin.cinescope.domain.model.Film

class FilmDiffCallback: DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}