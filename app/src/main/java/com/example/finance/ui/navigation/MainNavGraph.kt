package com.example.finance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finance.ui.screens.login.LoginScreen
import com.example.finance.ui.screens.landing.LandingScreen

object MainNavGraph {
    const val AUTH_ROUTE = "auth"
    const val LOGIN_ROUTE = "${AUTH_ROUTE}/login"
    const val REGISTRATION_ROUTE = "${AUTH_ROUTE}/registration"
    const val HOME_ROUTE = "home"
    const val LANDING_ROUTE = "landing"

    fun navigate(
        navController: NavController,
        destination: String
    ) {
        navController.navigate(destination)
    }
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainNavGraph.LANDING_ROUTE
    ) {
        composable(MainNavGraph.LANDING_ROUTE) {
            LandingScreen(navHostController = navController)
        }
        composable(MainNavGraph.LOGIN_ROUTE) {
            LoginScreen(

            )
        }
        composable(MainNavGraph.REGISTRATION_ROUTE) {
            RegistrationScreen(

            )
        }
        composable(MainNavGraph.HOME_ROUTE) {
            HomeScreen(

            )
        }
    }
}
