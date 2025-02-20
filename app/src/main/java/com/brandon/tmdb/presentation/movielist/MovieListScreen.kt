package com.brandon.tmdb.presentation.movielist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.brandon.tmdb.BuildConfig
import com.brandon.tmdb.domain.models.Movie

@Composable
fun MovieListScreen() {
    val viewModel: FavoriteMoviesViewModel = hiltViewModel()

    val favoriteMovies = viewModel.favoriteMovies.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            LazyRow {
                items(favoriteMovies.value) { movie ->
                    MovieItem(movie)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier =
            Modifier
                .size(
                    160.dp,
                    240.dp,
                ).padding(horizontal = 8.0.dp),
    ) {
        val baseImageUrl = BuildConfig.BASE_IMAGE_URL
        AsyncImage(
            modifier = Modifier.height(height = 160.dp),
            model = baseImageUrl + movie.posterPath,
            contentDescription = "Translated description of what the image contains",
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = movie.title,
            modifier = Modifier.padding(8.0.dp),
        )
        Text(
            text = movie.voteAverage.toString(),
            modifier = Modifier.padding(8.0.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieListScreen() {
    val favoriteMovies =
        listOf(
            Movie(
                id = 1234,
                title = "Moana 2",
                overview = "Avatar",
                posterPath = "/aLVkiINlIeCkcZIzb7XHzPYgO6L.jpg",
                voteAverage = 1.2f,
            ),
            Movie(
                id = 1235,
                title = "Fire",
                overview = "fire",
                posterPath = "/d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                voteAverage = 5.2f,
            ),
        )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            LazyRow {
                items(favoriteMovies) { movie ->
                    MovieItem(movie)
                }
            }
        }
    }
}
