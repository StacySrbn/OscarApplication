package com.example.homelibrary.domain.use_cases.auth

import com.example.homelibrary.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        return repository.login(email, password)
    }
}