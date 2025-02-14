package com.brandon.tmdb.presentation.movielist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.brandon.tmdb.domain.models.Movie

@Composable
fun MovieListScreen(viewModel: FavoriteMoviesViewModel = hiltViewModel()) {
    val favoriteMovies = viewModel.favoriteMovies.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Favorite Movies",
                style = MaterialTheme.typography.bodyLarge,
            ) // Header for favorite movies
        }
        items(favoriteMovies.value) { movie ->
            MovieItem(movie)
        }
        // You can add more items here for other movie lists if needed
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Text(text = movie.title) // Display movie title
    // You can add more details here, such as overview, poster, etc.
}
