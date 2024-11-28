package com.example.homelibrary

import androidx.lifecycle.*
import com.example.homelibrary.domain.manager.LocalUserManager
import com.example.homelibrary.presentation.navgraph.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUserManager: LocalUserManager,
    private val firebaseAuth: FirebaseAuth
): ViewModel() {

    private val _startDestination = MutableStateFlow<Screen>(Screen.OnBoardingScreen)
    val startDestination = _startDestination.asStateFlow()

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        viewModelScope.launch{
            localUserManager.readAppEntry().collect{ hasEnteredApp->
                if (!_isReady.value){
                    val isAuthenticated = checkUserAuthentication()
                    _startDestination.value = when {
                        !hasEnteredApp -> Screen.OnBoardingScreen
                        isAuthenticated -> Screen.CoreScreen
                        else -> Screen.SignInScreen
                    }
                    _isReady.value = true
                    println("App is ready: ${_isReady.value}")
                }
             }
        }
    }
    private fun checkUserAuthentication(): Boolean {
        if (firebaseAuth.currentUser!=null){
            println("User's already logged in: ${firebaseAuth.currentUser!=null}")
            return true
        }
        return false
    }
}

