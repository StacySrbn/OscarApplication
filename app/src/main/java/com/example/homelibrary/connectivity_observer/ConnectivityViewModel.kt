package com.example.homelibrary.connectivity_observer

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel @Inject constructor(
    application: Application,
    private val connectivityObserver: AndroidConnectivityObserver
): AndroidViewModel(application) {

    val isConnected: StateFlow<Boolean> = connectivityObserver
        .isConnected
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            true
        )

    private val _showPopup = MutableStateFlow(false)
    val showPopup: StateFlow<Boolean> = _showPopup

    fun setPopupVisibility(visible: Boolean) {
        _showPopup.value = visible
    }

    init {
        showConnectivityAlert()
    }

    private fun showConnectivityAlert(){
        viewModelScope.launch {
            isConnected
                .collectLatest { connected ->
                _showPopup.value = !connected
            }
        }
    }
}