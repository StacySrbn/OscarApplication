package com.example.homelibrary.presentation.navgraph

sealed class Screen(
    val route: String
){
    object OnBoardingScreen : Screen(route = "onboarding")
    object SignUpScreen : Screen(route = "signup")
    object SignInScreen : Screen(route = "sign_in")
    object HomeScreen : Screen(route = "home")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
