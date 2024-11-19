package com.example.homelibrary.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.homelibrary.R
import com.example.homelibrary.presentation.common.TopSectionBack
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.signup.components.InputFields
import com.example.homelibrary.presentation.signup.components.PasswordValidity
import com.example.homelibrary.presentation.signup.components.SignUpScreenButtons
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun SignUpScreen(
    state: SignUpState,
    navigate: (Screen) -> Unit,
    onSignUp: (navigate: (Screen) -> Unit) -> Unit,
    onErrorMessageShown: () -> Unit,
    onNameChange: (String) ->  Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    validatePassword: (String) -> PasswordValidity
){
    val context = LocalContext.current

    LaunchedEffect(state.isSignUpSuccessful, state.errorMessage) {
        if (state.isSignUpSuccessful){
            Toast.makeText(context, "Registration successful.", Toast.LENGTH_LONG).show()
        } else {
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                onErrorMessageShown()
            }
        }
    }

    val title = stringResource(id = R.string.signup_label)
    val subTitle = stringResource(id = R.string.signup_subtitle)

    LazyColumn (
        Modifier
            .background(colorResource(id = R.color.milk_white))
            .fillMaxHeight()
    ){
        item {
            TopSectionBack(
                iconVisibility = false,
                title = title,
                subTitle = subTitle
            )
        }
        item {
            InputFields(
                nameState = state.name,
                emailState = state.email,
                passwordState = state.password,
                passwordConfirmState = state.confirmPassword,
                onNameChanged = onNameChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onConfirmPasswordChange = onConfirmPasswordChange,
                validatePassword = validatePassword
            )
        }
        item {
            SignUpScreenButtons(
                onRegisterClick = {
                    onSignUp(navigate)
                },
                navigate = navigate

            )
        }
    }
}
