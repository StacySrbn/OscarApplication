package com.example.homelibrary

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.homelibrary.presentation.navgraph.NavGraph
import com.example.homelibrary.presentation.signin.SignInScreen
import com.example.homelibrary.presentation.signup.SignUpScreen
import com.example.homelibrary.ui.theme.HomeLibraryTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {

            setKeepOnScreenCondition{
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { splashScreenViewProvider ->
                val iconView = splashScreenViewProvider.iconView
                val scaleX = ObjectAnimator.ofFloat(iconView, View.SCALE_X, 0.4f, 0.0f)
                val scaleY = ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 0.4f, 0.0f)

                AnimatorSet().apply {
                    interpolator = OvershootInterpolator()
                    duration = 500L
                    playTogether(scaleX, scaleY)
                    doOnEnd { splashScreenViewProvider.remove() }
                    start()
                }
            }
        }
        setContent {
            HomeLibraryTheme {
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                }

                val startDestination = viewModel.startDestination.collectAsState().value
                val navController = rememberNavController()
                NavGraph(startDestination = startDestination, navController = navController)


            }
        }
    }
}