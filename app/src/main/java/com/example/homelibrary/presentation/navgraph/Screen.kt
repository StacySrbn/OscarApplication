package com.example.homelibrary.presentation.navgraph

sealed class Screen(
    val route: String
){
    object OnBoardingScreen: Screen("onboarding")
    object SignUpScreen: Screen("signup")
    object SignInScreen: Screen("signin")
    object CoreScreen: Screen("core")
    object DashboardScreen : Screen("dashboard")
    object CategoryScreen: Screen("category")
    object FavoriteScreen: Screen("favorite")
    object ProfileScreen: Screen("profile")
    object HelperScreen: Screen("helper")
    object DetailsScreen: Screen("details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
