package com.example.homelibrary.data.repository

import com.example.homelibrary.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override suspend fun login(email: String, password: String) {
        try{
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
           e.printStackTrace()
            when (e) {
                is FirebaseAuthInvalidUserException -> {
                    throw Exception("Account with this email does not exist.")
                }
                is FirebaseAuthInvalidCredentialsException -> {
                    throw Exception("Incorrect password for this email.")
                }
                is CancellationException -> {
                    throw e
                }
                else -> {
                    throw Exception("An error occurred: ${e.message}")
                }
            }
        }
    }

    override suspend fun register(email: String, password: String) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        } catch (e: Exception){
            e.printStackTrace()
            when (e) {
                is CancellationException -> throw e
                is FirebaseAuthUserCollisionException -> {
                    throw Exception("Account with this email already exists.")
                }
                else -> {
                    throw Exception("Registration failed: ${e.message}")
                }
            }
        }
    }
}