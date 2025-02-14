package com.brandon.tmdb.data.repository

import com.brandon.tmdb.data.api.MovieApiService
import com.brandon.tmdb.data.models.toModels
import com.brandon.tmdb.domain.models.Movie
import com.brandon.tmdb.domain.repository.FavoriteMovieRepository

class FavoriteMovieRepositoryImpl constructor(
    private val apiService: MovieApiService,
) : FavoriteMovieRepository {
    override suspend fun getMovies(): List<Movie> = apiService.getPopularMovies().results.toModels()
}
