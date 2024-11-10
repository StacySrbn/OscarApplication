package com.example.homelibrary.presentation.signin

import android.util.Log
import androidx.lifecycle.*
import com.example.homelibrary.domain.use_cases.auth.SignInUseCase
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.signin.google.SignInResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    private val _stateGoogle = MutableStateFlow(GoogleSignInState())
    val stateGoogle = _stateGoogle.asStateFlow()

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()



    fun onSignInResult(result: SignInResult) {
        _stateGoogle.update { it.copy(
            isGoogleSignInSuccessful = result.data != null,
            googleSignInError = result.errorMessage
        ) }
    }

    fun resetGoogleState(){
        _stateGoogle.update { GoogleSignInState() }
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
                password = password
            )
        }
    }
    fun signIn(email: String, password: String, navigate: (Screen) -> Unit) {

        val errorMessage = when {
            email.isBlank() -> "Email cannot be blank."
            password.isBlank() -> "Password cannot be blank."
            !_state.value.isEmailValid -> "Invalid email format."
            else -> null
        }
        if (errorMessage != null) {
            _state.update { it.copy(signInError = errorMessage) }
            return
        }
        viewModelScope.launch {
            try {
                signInUseCase(email, password)
                _state.update { it.copy(isSignInSuccessful = true, signInError = null) }
                Log.d("SignInViewModel", "Sign In Successful before catch block ${_state.value.isSignInSuccessful}")
            } catch (e: Exception) {
                Log.d("SignInViewModel", "Sign In Successful inside1 catch block ${_state.value.isSignInSuccessful}")
                _state.update { it.copy(isSignInSuccessful = false, signInError = "Account with this email does not exist.") }
                Log.d("SignInViewModel", "Sign In Successful inside2 catch block ${_state.value.isSignInSuccessful}")
            }
            Log.d("SignInViewModel", "Sign In Successful after catch block ${_state.value.isSignInSuccessful}")

            if (_state.value.isSignInSuccessful){
                navigate(Screen.HelperScreen)
                delay(2000)
                resetState()
            }
        }

    }

    private fun resetState(){
        _state.update { SignInState() }
    }
    fun onErrorMessageShown() {
        _state.update { it.copy(signInError = null) }
    }


}