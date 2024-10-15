package com.example.homelibrary.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.homelibrary.presentation.onboarding.OnBoardingEvent
import com.example.homelibrary.presentation.onboarding.OnBoardingScreen
import com.example.homelibrary.presentation.onboarding.OnBoardingViewModel
import com.example.homelibrary.presentation.signin.SignInScreen
import com.example.homelibrary.presentation.signup.SignUpScreen

@Composable
fun NavGraph(
    startDestination: Screen,
    navController: NavHostController
){
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()

    NavHost(
        startDestination = startDestination.route,
        navController = navController
    ){
        composable(Screen.OnBoardingScreen.route){
            OnBoardingScreen(onEvent = { event ->
                when (event) {
                    OnBoardingEvent.SaveAppEntry -> {
                        onBoardingViewModel.onEvent(OnBoardingEvent.SaveAppEntry)
                        println("Saved app entry")
                    }
                }
            }, navigate = { screen ->
                navController.navigate(screen.route)
            })
        }
        composable(Screen.SignInScreen.route){
            SignInScreen()
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen()
        }

    }
}