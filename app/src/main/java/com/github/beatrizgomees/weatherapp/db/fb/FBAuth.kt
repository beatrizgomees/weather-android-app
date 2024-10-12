package com.github.beatrizgomees.weatherapp.db.fb

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FBAuth {
    val currentUser = Firebase.auth.currentUser
    val currentUserFlow = callbackFlow {
        val authListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        Firebase.auth.addAuthStateListener(authListener)
        awaitClose { Firebase.auth.removeAuthStateListener(authListener) }
    }
    suspend fun signIn(email : String, password : String) : Boolean  = try {
        Firebase.auth
            .signInWithEmailAndPassword(email, password).await().user != null
    } catch (e : Throwable) {  false   }
    suspend fun signUp(email : String, password : String) : Boolean  = try {
        Firebase.auth
            .createUserWithEmailAndPassword(email, password).await().user != null
    } catch (e : Throwable) {  false  }
    fun singOut() =  Firebase.auth.signOut()
}