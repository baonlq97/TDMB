package com.brandon.tmdb

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface AppDestination {
    val icon: ImageVector
    val route: String
}

/**
 * TMDB app navigation destinations
 */
data object Favorite : AppDestination {
    override val icon = Icons.Filled.Favorite
    override val route = "favorite"
}

data object Saved : AppDestination {
    override val icon = Icons.Filled.Star
    override val route = "saved"
}

val appTabRowScreens = listOf(Favorite, Saved)
