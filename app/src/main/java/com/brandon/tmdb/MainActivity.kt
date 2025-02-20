package com.brandon.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.brandon.tmdb.ui.LinearNavigationGraphTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LinearNavigationGraphTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    appTabRowScreens.find { it.route == currentDestination?.route } ?: Favorite

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(currentScreen.route)
                            },
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            appTabRowScreens.forEach { appDestination ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            appDestination.icon,
                                            contentDescription = null,
                                        )
                                    },
                                    selected = currentScreen.route == appDestination.route,
                                    onClick = {
                                        navController.navigateSingleTopTo(appDestination.route)
                                    },
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
