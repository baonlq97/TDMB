package com.brandon.tmdb.presentation.savedlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SavedListScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Saved Movies",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
