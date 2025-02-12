package com.brandon.tmdb.data.api

import com.brandon.tmdb.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "YOUR_TMDB_API_KEY",
        @Query("page") page: Int = 1,
    ): MovieResponse
}
