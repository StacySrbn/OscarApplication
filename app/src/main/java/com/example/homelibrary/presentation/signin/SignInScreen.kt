package com.example.homelibrary.presentation.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.homelibrary.R
import com.example.homelibrary.presentation.signin.components.ButtonsLogin
import com.example.homelibrary.presentation.signin.components.EmailPasswordInputFields
import com.example.homelibrary.presentation.common.TopSectionBack

@Composable
fun SignInScreen() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val iconVisibility = true
    val title = stringResource(id = R.string.welcome_back_label)
    val subTitle = stringResource(id = R.string.sign_to_ur_acc_label)

    Column {
        TopSectionBack(iconVisibility, title, subTitle)
        EmailPasswordInputFields(
            emailState = emailState,
            passwordState = passwordState,
            onEmailChange = { emailState.value = it }, //change
            onPasswordChange = { passwordState.value = it } // change
        )
        ButtonsLogin()
    }

}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}