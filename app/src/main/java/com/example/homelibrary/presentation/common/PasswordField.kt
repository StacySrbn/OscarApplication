package com.example.homelibrary.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.presentation.signup.components.PasswordStrengthIndicator
import com.example.homelibrary.presentation.signup.components.PasswordValidity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    passwordState: String,
    onPasswordChange: (String) -> Unit,
    label: String,
    showRequirements: Boolean = false,
    validatePassword: (String) -> PasswordValidity = { PasswordValidity() }
){
    var passwordVisible by remember { mutableStateOf(false) }
    var requirementsVisible by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = passwordState,
            onValueChange = {
                onPasswordChange(it)
                if (showRequirements) {
                    requirementsVisible = true
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.ur_password_label),
                    color = colorResource(id = R.color.gray)
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible){
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ){
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colorResource(id = R.color.light_gray),
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = colorResource(id = R.color.graphite)
            )
        )

        if (showRequirements && requirementsVisible){
            val isPasswordValid = validatePassword(passwordState)
            PasswordStrengthIndicator(isPasswordValid)
        }
    }
}
