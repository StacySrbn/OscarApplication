package com.example.homelibrary.presentation.signup

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.homelibrary.R
import com.example.homelibrary.presentation.common.TopSectionBack
import com.example.homelibrary.presentation.signup.components.InputFields
import com.example.homelibrary.presentation.signup.components.SignUpScreenButtons
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun SignUpScreen(){

    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordConfirmState = remember { mutableStateOf("") }

    val title = stringResource(id = R.string.signup_label)
    val subTitle = stringResource(id = R.string.signup_subtitle)

    LazyColumn {
        item {
            TopSectionBack(
                iconVisibility = false,
                title = title,
                subTitle = subTitle
            )
        }
        item {
            InputFields(
                nameState = nameState,
                emailState = emailState,
                passwordState = passwordState,
                passwordConfirmState = passwordConfirmState,
                onNameChanged = { nameState.value = it },
                onEmailChange = { emailState.value = it },
                onPasswordChange = { passwordState.value = it },
                onConfirmPasswordChange = { passwordConfirmState.value = it }
            )
        }
        item {
            SignUpScreenButtons()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    HomeLibraryTheme {
        SignUpScreen()
    }
}