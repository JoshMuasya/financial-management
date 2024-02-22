package com.example.finance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finance.ui.navigation.MainNavGraph
import com.example.finance.ui.screens.landing.LandingScreen
import com.example.finance.ui.screens.login.LoginScreen
import com.example.finance.ui.screens.login.LoginViewModel
import com.example.finance.ui.screens.registration.RegistrationScreen

@Composable
fun Navigation(
    viewModel: LoginViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Landing"
    ) {
        composable("Landing") {
            LandingScreen(navHostController = navController)
        }
        composable("Login") {
            LoginScreen(
                viewModel = viewModel,
                onRegisterClick = {
                    navController.navigate(MainNavGraph.REGISTRATION_ROUTE)
                }
            )
        }
        composable("Signup") {
            RegistrationScreen(
                onLoginClick = {
                    navController.navigate(MainNavGraph.LOGIN_ROUTE)
                }
            )
        }
    }
}