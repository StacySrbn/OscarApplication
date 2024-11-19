package com.example.homelibrary

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.*
import androidx.activity.compose.*
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.homelibrary.presentation.navgraph.AppNavigation
import com.example.homelibrary.presentation.signin.SignInViewModel
import com.example.homelibrary.presentation.signin.google.GoogleAuthUiClient
import com.example.homelibrary.ui.theme.HomeLibraryTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()


    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {

            setKeepOnScreenCondition {
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
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)

                /*val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent
                    )
                }*/

                val startDestination = viewModel.startDestination.collectAsState().value
                val navController = rememberNavController()
                val signInViewModel: SignInViewModel = hiltViewModel()

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if (result.resultCode == RESULT_OK) {
                            lifecycleScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                signInViewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                val onGoogleSignInClick: () -> Unit = {
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }

                AppNavigation(
                    startDestination = startDestination,
                    navController = navController,
                    onGoogleSignInClick = onGoogleSignInClick,
                    signInViewModel = signInViewModel

                )
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color){
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = color) {
        systemUiController.setSystemBarsColor(color)
    }
}