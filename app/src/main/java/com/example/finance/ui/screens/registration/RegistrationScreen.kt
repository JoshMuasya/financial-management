package com.example.finance.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finance.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    viewModel: RegistrationViewModel
) {
    LaunchedEffect(viewModel.navigateToLogin) {
        if (viewModel.navigateToLogin.value) {
            viewModel.onNavigatedToLogin()
        }
    }

    Surface (
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo"
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = "Fill in the Details to Register",
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .padding(6.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.email.value ?: "",
                onValueChange = { viewModel.email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = modifier.height(5.dp))

            OutlinedTextField(
                value = viewModel.phonenumber.value ?: "",
                onValueChange = { viewModel.phonenumber.value = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = modifier.height(5.dp))

            OutlinedTextField(
                value = viewModel.password.value ?: "",
                onValueChange = { viewModel.password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = modifier.height(5.dp))

            OutlinedTextField(
                value = viewModel.confirmPassword.value ?: "",
                onValueChange = { viewModel.confirmPassword.value = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = modifier.height(16.dp))

            if (viewModel.isRegister.value == true) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                com.example.finance.ui.components.Button(
                    onClick = { viewModel.register() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            if (viewModel.errorMessage.value != null) {
                Text(
                    text = viewModel.errorMessage.value!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            TextButton(
                onClick = onLoginClick
            ) {
                Text(text = "Have an Account?")
            }
        }
    }
}