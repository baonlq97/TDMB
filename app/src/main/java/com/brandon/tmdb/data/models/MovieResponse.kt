package com.brandon.tmdb.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
)

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("vote_average")
    val voteAverage: Float,
)

fun Movie.toModel() =
    com.brandon.tmdb.domain.models
        .Movie(
            id = this.id,
            title = this.title,
            overview = this.overview,
            posterPath = this.posterPath,
            voteAverage = this.voteAverage,
        )

fun List<Movie>.toModels() = this.map { it.toModel() }
