package com.example.homelibrary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.signup.SignUpScreen
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun HelperScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your next screen",
            color = Color.Green,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HelperPreview(){
    HomeLibraryTheme {
        HelperScreen()
    }
}