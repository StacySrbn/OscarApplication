package com.example.homelibrary.presentation.navgraph

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.homelibrary.presentation.profile.HelperScreen
import com.example.homelibrary.presentation.core.CoreScreen
import com.example.homelibrary.presentation.details.DetailsScreen
import com.example.homelibrary.presentation.details.DetailsViewModel
import com.example.homelibrary.presentation.onboarding.OnBoardingEvent
import com.example.homelibrary.presentation.onboarding.OnBoardingScreen
import com.example.homelibrary.presentation.onboarding.OnBoardingViewModel
import com.example.homelibrary.presentation.signin.SignInScreen
import com.example.homelibrary.presentation.signin.SignInViewModel
import com.example.homelibrary.presentation.signup.SignUpScreen
import com.example.homelibrary.presentation.signup.SignUpViewModel

@Composable
fun AppNavigation(
    startDestination: Screen,
    navController: NavHostController,
    onGoogleSignInClick: () -> Unit,
    signInViewModel: SignInViewModel
){
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    val context = LocalContext.current

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

            val googleState by signInViewModel.stateGoogle.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = googleState.isGoogleSignInSuccessful){
                if(googleState.isGoogleSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Screen.CoreScreen.route)
                    signInViewModel.resetGoogleState()
                }
            }

            val viewModel = hiltViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsState()

            SignInScreen(
                state = state,
                googleSignInState = googleState,
                onGoogleSignInClick = onGoogleSignInClick,
                navigate = { screen -> navController.navigate(screen.route) },
                onErrorMessageShown = {viewModel.onErrorMessageShown()},
                onEmailChange = { email -> viewModel.onEmailChange(email) },
                onPasswordChange = { password -> viewModel.onPasswordChange(password) },
                onSignIn = { email, password, navigate ->
                    viewModel.signIn(email, password) { screen ->
                        navigate(screen)
                    }
                }
            )

        }
        composable(Screen.SignUpScreen.route){
            val viewModel = hiltViewModel<SignUpViewModel>()
            val state by viewModel.state.collectAsState()
            SignUpScreen(
                state = state,
                onErrorMessageShown = {viewModel.onErrorMessageShown()},
                navigate = { screen -> navController.navigate(screen.route) },
                onSignUp = { navigate ->
                    viewModel.signUp{screen ->
                        navigate(screen)
                    }
                },
                onNameChange = { name -> viewModel.onNameChange(name) },
                onEmailChange = { email -> viewModel.onEmailChange(email) },
                onPasswordChange = { password -> viewModel.onPasswordChange(password) },
                onConfirmPasswordChange = { confirmPassword -> viewModel.onConfirmPasswordChange(confirmPassword) },
                validatePassword = { password -> viewModel.validatePassword(password) }            )
        }
        composable(Screen.CoreScreen.route) {
            CoreScreen(navController)
        }
        composable(
            route = Screen.DetailsScreen.withArgs("{movieId}"),
            arguments = listOf(
                navArgument(name = "movieId") {type = NavType.IntType}
            )
        ) {
            val detailsViewModel = hiltViewModel<DetailsViewModel>()
            val detailsState = detailsViewModel.detailsState.collectAsState().value
            val genreState = detailsViewModel.genreState.collectAsState().value
            val trailerState = detailsViewModel.trailerState.collectAsState().value

            DetailsScreen(detailsState = detailsState, navController = navController, genreState = genreState, trailerState = trailerState)
        }
        composable(Screen.HelperScreen.route) {
            HelperScreen(
                onSignOutComplete = {
                    navController.navigate(Screen.SignInScreen.route){
                        popUpTo(Screen.HelperScreen.route) { inclusive = true }
                    }

                }
            )
        }


    }
}