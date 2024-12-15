package com.example.homelibrary.presentation.core

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.homelibrary.R
import com.example.homelibrary.connectivity_observer.ConnectivityViewModel
import com.example.homelibrary.connectivity_observer.InternetConnectionPopup
import com.example.homelibrary.presentation.core.dashboard.DashboardScreen
import com.example.homelibrary.presentation.core.dashboard.DashboardViewModel
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Constants.UPCOMING_CATEGORY
import kotlinx.coroutines.delay

@Composable
fun CoreScreen(navController: NavHostController){


    val connectivityViewModel: ConnectivityViewModel = hiltViewModel()
    val isConnected by connectivityViewModel.isConnected.collectAsStateWithLifecycle()
    val showPopup by connectivityViewModel.showPopup.collectAsStateWithLifecycle()
    println("CORE: showPopup: $showPopup | is connected: $isConnected")


    val bottomNavController = rememberNavController()

    val dashboardViewModel = hiltViewModel<DashboardViewModel>()
    val movieListState = dashboardViewModel.movieListState.collectAsState().value
    val bannersState = dashboardViewModel.bannersState.collectAsState().value
    val actorListState = dashboardViewModel.actorListState.collectAsState().value

    val popularMovieList = movieListState.popularMovieList.collectAsLazyPagingItems()
    val upcomingMovieList = movieListState.upcomingMovieList.collectAsLazyPagingItems()
    val actorList = actorListState.actorList.collectAsLazyPagingItems()

    Log.d("MovieRemoteMediator", "popular list size ${popularMovieList.itemCount}")
    Log.d("MovieRemoteMediator", "upcoming list size ${upcomingMovieList.itemCount}")


    if (showPopup) {
        InternetConnectionPopup(
            showPopup = true,
            onDismiss = { connectivityViewModel.setPopupVisibility(false) }
        )
    }

    Scaffold(
        topBar = { null },
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.milk_white))
                .fillMaxSize()
                .padding(it)
        ){
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.DashboardScreen.route
            ){
                composable(Screen.DashboardScreen.route) {

                    var isRefreshing by remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(isRefreshing) {
                        if (isRefreshing) {
                            dashboardViewModel.refreshData()
                            delay(1000)
                            isRefreshing = false
                        }
                    }
                    DashboardScreen(
                        navController = navController,
                        bannersState = bannersState,
                        movieListState = movieListState,
                        popularMovieList = popularMovieList,
                        upcomingMovieList = upcomingMovieList,
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            isRefreshing = true
                        },
                        onReloadBanners = { dashboardViewModel.reloadBanners() },
                        onReloadPopular = { dashboardViewModel.reloadMovieList(POPULAR_CATEGORY) },
                        onReloadUpcoming = { dashboardViewModel.reloadMovieList(UPCOMING_CATEGORY) },
                        actorListState = actorListState,
                        actorList = actorList
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

