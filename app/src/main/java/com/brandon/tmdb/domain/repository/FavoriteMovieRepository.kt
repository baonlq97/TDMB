package com.brandon.tmdb.domain.repository

import com.brandon.tmdb.domain.models.Movie

interface FavoriteMovieRepository {
    suspend fun getMovies(): List<Movie>
}
