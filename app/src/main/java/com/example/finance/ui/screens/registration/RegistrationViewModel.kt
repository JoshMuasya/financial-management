package com.example.finance.ui.screens.registration

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
class RegistrationViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val application: Application
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val phonenumber = MutableLiveData<String>()
    val isRegister = MutableLiveData(false)
    val errorMessage = MutableLiveData<String?>()

    private val _navigateToLogin = MutableStateFlow<Boolean>(false)
    val navigateToLogin: StateFlow<Boolean> = _navigateToLogin

    fun register() {
        isRegister.value = true
        errorMessage.value = null

        val emailText = email.value ?: ""
        val passwordText = password.value ?: ""
        val confirmPasswordText = confirmPassword.value ?: ""
        val phonenumberText = phonenumber.value ?: ""

        if (validateInput(emailText, passwordText, confirmPasswordText, phonenumberText)) {
            auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _navigateToLogin.value = true
                        Toast.makeText(
                            application.applicationContext,
                            "Registration Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        errorMessage.value = task.exception?.localizedMessage ?: "Registration Failed"
                    }
                    isRegister.value = false
                }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String, phonenumber: String): Boolean {
        if (email.isEmpty()) {
            errorMessage.value = "Please enter your Email."
            return false
        }

        if (password.isEmpty()) {
            errorMessage.value = "Please enter your Password"
            return false
        }

        if (confirmPassword.isEmpty()) {
            errorMessage.value = "Please confirm your Password"
            return false
        }

        if (phonenumber.isEmpty()) {
            errorMessage.value = "Please enter your Phone Number"
            return false
        }

        if (password !== confirmPassword) {
            errorMessage.value = "Passwords do not match"
            return false
        }
        return true
    }

    fun onNavigatedToLogin() {
        _navigateToLogin.value = false
    }
}