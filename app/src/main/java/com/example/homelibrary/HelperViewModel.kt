package com.example.homelibrary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homelibrary.presentation.signin.google.GoogleAuthUiClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HelperViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val firestore: FirebaseFirestore
): ViewModel(){

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    fun fetchUserName(userId: String) {
        viewModelScope.launch {
            try {
                val user = Firebase.auth.currentUser
                if (user!= null){
                    if (user.providerData.any {it.providerId == "google.com"}){
                        _userName.value = user.displayName ?: "Unknown User"
                    } else {
                        val userDoc = firestore.collection("users").document(userId).get().await()
                        _userName.value = userDoc.getString("name") ?: "Unknown User"
                    }
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun signOut(onSignOutComplete: () -> Unit){
        viewModelScope.launch {
            try {
                val user = Firebase.auth.currentUser
                if (user!=null){
                    if (user.providerData.any { it.providerId == "google.com" }) {
                        googleAuthUiClient.signOut()
                    } else {
                        Firebase.auth.signOut()
                    }
                }
                println("User has successfully signed out.")
                onSignOutComplete()
            } catch (e: Exception){
                e.printStackTrace()
                println("Sign-out failed: ${e.message}")
            }
        }
    }

}