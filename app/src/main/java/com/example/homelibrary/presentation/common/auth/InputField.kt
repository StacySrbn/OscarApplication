package com.example.homelibrary.presentation.common.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.R

@Composable
fun InputField(
    label: String,
    hint: String,
    fieldState: String,
    onFieldChange: (String) -> Unit
){
    Column {
        Text(
            text = label,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = fieldState,
            onValueChange = onFieldChange,
            label = {
                Text(
                    text = hint,
                    color = colorResource(id = R.color.graphite),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.light_gray),
                focusedContainerColor = colorResource(id = R.color.light_gray)

            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    MaterialTheme {
        InputField(
            label = "Email",
            hint = "Enter your email",
            fieldState = "",
            onFieldChange = {}
        )
    }
}