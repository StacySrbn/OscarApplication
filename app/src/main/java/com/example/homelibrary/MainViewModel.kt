package com.example.homelibrary

import androidx.lifecycle.*
import com.example.homelibrary.domain.manager.LocalUserManager
import com.example.homelibrary.presentation.navgraph.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUserManager: LocalUserManager
): ViewModel() {

    private val _startDestination = MutableStateFlow<Screen>(Screen.OnBoardingScreen)
    val startDestination = _startDestination.asStateFlow()

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()


//    init {
//        viewModelScope.launch {
//            // Simulate some loading time
//            delay(2000)
//            _isReady.value = true
//            println("App is ready: ${_isReady.value}")
//        }
//    }

    init {
        viewModelScope.launch{
            localUserManager.readAppEntry().collect{ hasEnteredApp->
                if (!_isReady.value){
                    val isAuthenticated = checkUserAuthentication()
                    _startDestination.value = when {
                        !hasEnteredApp -> Screen.OnBoardingScreen
                        isAuthenticated -> Screen.HomeScreen
                        else -> Screen.SignInScreen
                    }
                    _isReady.value = true
                    println("App is ready: ${_isReady.value}")
                }
             }
        }
    }

    private fun checkUserAuthentication(): Boolean {
        // Implement your authentication check logic here
        return false
    }
}

