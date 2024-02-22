package com.example.finance.ui.screens.login

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.R
import com.example.finance.ui.components.Button

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel
) {
    LaunchedEffect(viewModel.navigateToHome) {
        if (viewModel.navigateToHome.value) {
            viewModel.onNavigatedToHome()
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
                text = "Fill in the Details to Login",
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
                value = viewModel.password.value ?: "",
                onValueChange = { viewModel.password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = modifier.height(16.dp))

            if (viewModel.isLoggingIn.value == true) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Button(
                    onClick = { viewModel.login() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
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

            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontStyle = FontStyle.Italic,
                    fontSize = 10.sp
                ),
                modifier = modifier
                    .padding(6.dp)
            )

            Spacer(modifier = modifier.height(10.dp))

            TextButton(
                onClick = onRegisterClick
            ) {
                Text(text = "Create Account")
            }

        }
    }
}