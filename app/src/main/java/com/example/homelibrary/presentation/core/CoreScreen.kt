package com.example.homelibrary.presentation.core

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.homelibrary.presentation.core.dashboard.DashboardScreen
import com.example.homelibrary.presentation.core.dashboard.DashboardViewModel
import com.example.homelibrary.presentation.core.dashboard.MovieListUiEvent
import com.example.homelibrary.presentation.navgraph.Screen

@Composable
fun CoreScreen(navController: NavHostController){

    val dashboardViewModel = hiltViewModel<DashboardViewModel>()
    val movieListState = dashboardViewModel.movieListState.collectAsState().value

    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = { null },
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.DashboardScreen.route
            ){
                composable(Screen.DashboardScreen.route) {
                    DashboardScreen(
                        navController = navController,
                        newsCards = emptyList(),
                        movieListState = movieListState,
                        oneEvent = dashboardViewModel::onEvent
                    )
                }
                composable(Screen.CategoryScreen.route) {

                }
                composable(Screen.FavoriteScreen.route) {

                }
                composable(Screen.ProfileScreen.route) {

                }
            }
        }
    }

}

