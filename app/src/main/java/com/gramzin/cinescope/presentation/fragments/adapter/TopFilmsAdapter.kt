package com.gramzin.cinescope.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gramzin.cinescope.R
import com.gramzin.cinescope.databinding.FilmHolderBinding
import com.gramzin.cinescope.domain.model.Film

class TopFilmsAdapter: PagingDataAdapter<Film, TopFilmsAdapter.TopFilmHolder>(FilmDiffCallback()) {

    override fun onBindViewHolder(holder: TopFilmHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopFilmHolder {
        val binding = FilmHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return TopFilmHolder(binding)
    }

    class TopFilmHolder(private val binding: FilmHolderBinding):
        RecyclerView.ViewHolder(binding.root) {

        val ratingRegex = """\d{1}\.\d{1}""".toRegex()

        fun bind(film: Film?){
            Glide
                .with(binding.root)
                .load(film?.posterPreview)
                .placeholder(R.drawable.poster)
                .into(binding.posterImage)
            if(film != null){
                film.rating?.let{
                    if (ratingRegex.matches(it)){
                        binding.ratingBack.visibility = View.VISIBLE
                        binding.rating.text = it
                    }
                    else{
                        binding.ratingBack.visibility = View.GONE
                        binding.rating.text = ""
                    }
                }
                binding.filmName.text = film.name

                val nameEn = if (film.nameEn != null) film.nameEn + ", " else ""
                val year = if (film.year != null) film.year + ", " else ""
                val filmLen = if (film.filmLength != null) film.filmLength + " мин." else ""
                binding.filmDesc1.text = nameEn + year + filmLen

                val countries = film.countries?.take(3)?.joinToString(separator = ", ")
                val genres = film.genres?.take(4)?.joinToString(separator = ", ")
                binding.filmDesc2.text = countries + " • " + genres
            }

        }
    }
}