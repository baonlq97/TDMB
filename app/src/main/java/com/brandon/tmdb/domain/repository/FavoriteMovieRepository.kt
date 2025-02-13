package com.brandon.tmdb.domain.repository

import com.brandon.tmdb.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getMovies(): Flow<List<Movie>>
}