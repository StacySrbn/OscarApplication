package com.example.homelibrary.presentation.signin

data class GoogleSignInState(
    val isGoogleSignInSuccessful: Boolean = false,
    val googleSignInError: String? = null
)

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)