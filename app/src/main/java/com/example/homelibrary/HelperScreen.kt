package com.example.homelibrary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.signup.SignUpScreen
import com.example.homelibrary.ui.theme.HomeLibraryTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HelperScreen(
    viewModel: HelperViewModel = hiltViewModel(),
    onSignOutComplete: () -> Unit
){
    val user = Firebase.auth.currentUser
    val userName = viewModel.userName.value

    LaunchedEffect(user) {
        user?.uid?.let { viewModel.fetchUserName(it) }
    }

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
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = userName,
            color = Color.Green,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                viewModel.signOut(onSignOutComplete)
            }
        ) {
            Text(text = "Sign Out")
        }
    }
}