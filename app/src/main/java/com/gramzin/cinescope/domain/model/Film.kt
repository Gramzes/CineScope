package com.gramzin.cinescope.domain.model

data class Film(var id: Int,
                var name: String?,
                var nameEn: String?,
                var year: String?,
                var filmLength: String?,
                var countries: List<String>?,
                var genres: List<String>?,
                var rating: String?,
                var poster: String?)