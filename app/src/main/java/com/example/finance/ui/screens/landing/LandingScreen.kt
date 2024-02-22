package com.example.finance.ui.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finance.R

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo"
            )

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = "Welcome",
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .padding(6.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = "DIGIMATIC FINANCIAL",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "ASSISTANT",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .padding(5.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            Button(
                onClick = {
                    navHostController.navigate("Login")
                }
            ) {
                Text(
                    text = "LOGIN",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                )
            }
        }
    }
}