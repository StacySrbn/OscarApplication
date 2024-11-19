package com.example.homelibrary.presentation.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.homelibrary.R
import com.example.homelibrary.presentation.signin.components.ButtonsLogin
import com.example.homelibrary.presentation.signin.components.EmailPasswordInputFields
import com.example.homelibrary.presentation.common.TopSectionBack
import com.example.homelibrary.presentation.navgraph.Screen

@Composable
fun SignInScreen(
    state: SignInState,
    googleSignInState: GoogleSignInState,
    onGoogleSignInClick:() -> Unit,
    navigate: (Screen) -> Unit,
    onErrorMessageShown: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: (String, String, navigate: (Screen) -> Unit) -> Unit,
) {
    val context = LocalContext.current

    val iconVisibility = true
    val title = stringResource(id = R.string.welcome_back_label)
    val subTitle = stringResource(id = R.string.sign_to_ur_acc_label)

    LaunchedEffect(state.isSignInSuccessful) {
        if (state.isSignInSuccessful){
            Toast.makeText(context, "Login successful.", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(state.signInError) {
        state.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            onErrorMessageShown()
        }
    }


    LaunchedEffect (key1 = googleSignInState.googleSignInError){
        googleSignInState.googleSignInError?.let { error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier.background(colorResource(id = R.color.milk_white))
    ) {
        TopSectionBack(iconVisibility, title, subTitle)
        EmailPasswordInputFields(
            emailState = state.email,
            passwordState = state.password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange
        )
        ButtonsLogin(
            onGoogleSignInClick = onGoogleSignInClick,
            navigate = navigate,
            onSignInClick = {
                onSignIn(
                    state.email,
                    state.password,
                    navigate
                )
            }
        )
    }
}
