package com.example.homelibrary.presentation.signup

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordValid: Boolean = false,
    val isPasswordMatching: Boolean = false,
    val isEmailValid: Boolean = false,
    val isSignUpSuccessful: Boolean = false,
    val errorMessage: String? = null
)
