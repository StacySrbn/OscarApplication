package com.example.homelibrary.presentation.signup

import android.util.Log
import androidx.lifecycle.*
import com.example.homelibrary.domain.use_cases.auth.SignUpUseCase
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.signin.SignInState
import com.example.homelibrary.presentation.signup.components.PasswordValidity
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun onEmailChange(email: String) {
        _state.update {
            it.copy(
                email = email,
                isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            )
        }
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(
                password = password,
                isPasswordValid = validatePassword(password).isValid(),
                isPasswordMatching = password == _state.value.confirmPassword
            )
        }
    }

    fun validatePassword(password: String): PasswordValidity {
        return PasswordValidity(
            isLengthValid = password.length >= 8,
            isNumberPresent = password.any { it.isDigit() },
            isLetterCaseValid = password.any { it.isUpperCase() } || password.any { it.isLowerCase() }
        )
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update {
            it.copy(
                confirmPassword = confirmPassword,
                isPasswordMatching = confirmPassword == _state.value.password
            )
        }
    }

    fun signUp(navigate: (Screen) -> Unit) {
        val currentState = _state.value
        val errorMessage = when {
            currentState.name.isBlank() -> "Name cannot be blank."
            currentState.email.isBlank() -> "Email cannot be blank."
            !currentState.isEmailValid -> "Invalid email address."
            currentState.password.isBlank() -> "Password cannot be blank."
            !currentState.isPasswordValid -> "Password must be at least 8 characters long and contain letters and digits."
            currentState.confirmPassword.isBlank() -> "Confirm Password cannot be blank."
            !currentState.isPasswordMatching -> "Passwords do not match."
            else -> null
        }

        if (errorMessage != null) {
            _state.update { it.copy(errorMessage = errorMessage, isSignUpSuccessful = false) }
            return
        }

        viewModelScope.launch {
            try {
                signUpUseCase(currentState.email, currentState.password)
                _state.update { it.copy(isSignUpSuccessful = true, errorMessage = null)}
                //val user = firebaseAuth.currentUser ?: throw Exception("Registration failed.")
                //saveUserProfile(user)
            } catch (e: Exception) {
                _state.update { it.copy(isSignUpSuccessful = false, errorMessage = "Email is already in use.") }
            }
            if (_state.value.isSignUpSuccessful) {
                navigate(Screen.HelperScreen)
                delay(2000)
                resetState()
            }
        }
    }

    private fun resetState(){
        _state.update { SignUpState() }
    }
    fun onErrorMessageShown() {
        _state.update { it.copy(errorMessage = null) }
    }

    /*private suspend fun saveUserProfile(user: FirebaseUser) {
     val currentState = _state.value
     val userData = mapOf(
         "name" to currentState.name,
         "email" to currentState.email
     )
     firestore.collection("users").document(user.uid).set(userData).await()
 }*/

}