package com.example.finance.ui.screens.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltAndroidApp
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val application: Application
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoggingIn = MutableLiveData(false)
    val errorMessage = MutableLiveData<String?>()

    private val _navigateToHome = MutableStateFlow<Boolean>(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    fun login() {
        isLoggingIn.value = true
        errorMessage.value = null

        val emailText = email.value ?: ""
        val passwordText = password.value ?: ""

        if (validateInput(emailText, passwordText)) {
            auth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _navigateToHome.value = true
                        Toast.makeText(
                            application.applicationContext,
                            "Login Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Login Failed"
                    }
                    isLoggingIn.value = false
                }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            errorMessage.value = "Please enter your Email"
            return false
        }
        if (password.isEmpty()) {
            errorMessage.value = "Please enter your Password"
            return false
        }
        return true
    }

    fun onNavigatedToHome() {
        _navigateToHome.value = false
    }
}