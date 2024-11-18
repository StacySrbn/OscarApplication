package com.example.homelibrary.presentation.signin.google

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val pfpUrl: String?
)
