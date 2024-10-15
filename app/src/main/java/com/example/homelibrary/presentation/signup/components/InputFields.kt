package com.example.homelibrary.presentation.signup.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.homelibrary.R
import com.example.homelibrary.presentation.Dimens
import com.example.homelibrary.presentation.common.InputField
import com.example.homelibrary.presentation.common.PasswordField

@Composable
fun InputFields(
    nameState: MutableState<String>,
    emailState: MutableState<String>,
    passwordState: MutableState<String>,
    passwordConfirmState: MutableState<String>,
    onNameChanged: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dimens.MediumPadding)
    ) {
        val nameLabel = stringResource(id = R.string.name_label)
        val nameHint = stringResource(id = R.string.name_hint_label)
        InputField(
            label = nameLabel,
            hint = nameHint,
            fieldState = nameState,
            onFieldChange = onNameChanged
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))

        val emailLabel = stringResource(id = R.string.email_label)
        val emailHint = stringResource(id = R.string.ur_email_label)
        InputField(
            label = emailLabel,
            hint = emailHint,
            onFieldChange = onEmailChange,
            fieldState = emailState
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))

        val labelPassword = stringResource(id = R.string.password_label)
        PasswordField(
            passwordState = passwordState,
            onPasswordChange = onPasswordChange,
            label = labelPassword
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))

        val labelConfirmPassword = stringResource(id = R.string.confirm_password_label)
        PasswordField(
            passwordState = passwordConfirmState,
            onPasswordChange = onConfirmPasswordChange,
            label = labelConfirmPassword
        )

    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldsPreview() {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordConfirmState = remember { mutableStateOf("") }

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