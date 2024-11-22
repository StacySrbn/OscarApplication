package com.example.homelibrary.presentation.signin.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.auth.InputField
import com.example.homelibrary.presentation.common.auth.PasswordField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordInputFields(
    emailState: String,
    passwordState: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MediumPadding)
    ) {

        val labelEmail = stringResource(id = R.string.email_label)
        val hint = stringResource(id = R.string.ur_email_label)
        InputField(
            label = labelEmail,
            hint = hint,
            onFieldChange = onEmailChange,
            fieldState = emailState
        )
        Spacer(modifier = Modifier.height(SmallPadding))

        val labelPassword = stringResource(id = R.string.password_label)
        PasswordField(
            passwordState = passwordState,
            onPasswordChange = onPasswordChange,
            label = labelPassword
        )
        Spacer(modifier = Modifier.height(SmallPadding))

        Text(
            text = stringResource(id = R.string.forgot_password_label),
            color = colorResource(id = R.color.teal_main),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }

}