package com.eslirodrigues.serviceappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eslirodrigues.serviceappcompose.landing.LandingScreen
import com.eslirodrigues.serviceappcompose.management.ManagementScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.LandingScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.ManagementScreen

@Composable
fun ServiceAppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LandingScreen) {
        composable<LandingScreen> {
            LandingScreen(
                onLoginClick = { navController.navigate(ManagementScreen) },
            )
        }
        composable<ManagementScreen> {
            ManagementScreen(
                onLogout = { navController.navigate(LandingScreen) }
            )
        }
    }
}