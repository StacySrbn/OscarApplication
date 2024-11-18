package com.example.homelibrary.presentation.signup.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homelibrary.R

data class PasswordValidity(
    val isLengthValid: Boolean = false,
    val isNumberPresent: Boolean = false,
    val isLetterCaseValid: Boolean = false
){
    fun isValid() = isLengthValid && isNumberPresent && isLetterCaseValid
}
@Composable
fun PasswordStrengthIndicator(passwordValidity: PasswordValidity) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = if (passwordValidity.isLengthValid) Icons.Rounded.Check else Icons.Rounded.Close,
            tint = if (passwordValidity.isLengthValid) Color.Green else Color.Red,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.min_chars_label))
        Spacer(modifier = Modifier.height(4.dp))
    }
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = if (passwordValidity.isNumberPresent) Icons.Rounded.Check else Icons.Rounded.Close,
            tint = if (passwordValidity.isNumberPresent) Color.Green else Color.Red,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.num_present_label))
        Spacer(modifier = Modifier.height(4.dp))
    }
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = if (passwordValidity.isLetterCaseValid) Icons.Rounded.Check else Icons.Rounded.Close,
            tint = if (passwordValidity.isLetterCaseValid) Color.Green else Color.Red,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.char_presence_label))
        Spacer(modifier = Modifier.height(4.dp))
    }

}

