package com.brandon.tmdb.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getModels(): Flow<List<Model>>
}