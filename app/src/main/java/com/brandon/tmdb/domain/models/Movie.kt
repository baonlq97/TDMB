package com.brandon.tmdb.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Float,
)
