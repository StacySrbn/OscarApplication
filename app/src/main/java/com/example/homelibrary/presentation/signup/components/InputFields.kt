package com.example.homelibrary.presentation.signup.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.auth.InputField
import com.example.homelibrary.presentation.common.auth.PasswordField

@Composable
fun InputFields(
    nameState: String,
    emailState: String,
    passwordState: String,
    passwordConfirmState: String,
    onNameChanged: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    validatePassword: (String) -> PasswordValidity
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
        Spacer(modifier = Modifier.height(SmallPadding))

        val emailLabel = stringResource(id = R.string.email_label)
        val emailHint = stringResource(id = R.string.ur_email_label)
        InputField(
            label = emailLabel,
            hint = emailHint,
            onFieldChange = onEmailChange,
            fieldState = emailState
        )
        Spacer(modifier = Modifier.height(SmallPadding))

        val labelPassword = stringResource(id = R.string.password_label)
        PasswordField(
            passwordState = passwordState,
            onPasswordChange = onPasswordChange,
            label = labelPassword,
            showRequirements = true,
            validatePassword = validatePassword
        )
        Spacer(modifier = Modifier.height(SmallPadding))

        val labelConfirmPassword = stringResource(id = R.string.confirm_password_label)
        PasswordField(
            passwordState = passwordConfirmState,
            onPasswordChange = onConfirmPasswordChange,
            label = labelConfirmPassword
        )

    }
}