package com.brandon.tmdb.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.tmdb.domain.models.Movie
import com.brandon.tmdb.domain.repository.FavoriteMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel
    @Inject
    constructor(
        private val repository: FavoriteMovieRepository,
    ) : ViewModel() {
        private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
        val favoriteMovies = _favoriteMovies.asStateFlow()

        init {
            fetchFavoriteMovies()
        }

        private fun fetchFavoriteMovies() {
            viewModelScope.launch {
                val data = repository.getMovies()
            }
        }
    }
