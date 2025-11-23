package com.eslirodrigues.serviceappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eslirodrigues.serviceappcompose.auth.MemberAuthScreen
import com.eslirodrigues.serviceappcompose.auth.PricingAuthScreen
import com.eslirodrigues.serviceappcompose.landing.LandingScreen
import com.eslirodrigues.serviceappcompose.member.MemberScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.LandingScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.MemberAuthScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.MemberScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.PricingAuthScreen
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavRoutes.PricingScreen
import com.eslirodrigues.serviceappcompose.pricing.PricingScreen

@Composable
fun ServiceAppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LandingScreen) {
        composable<LandingScreen> {
            LandingScreen(
                onLoginClick = { navController.navigate(MemberAuthScreen) },
            )
        }
        composable<MemberAuthScreen> {
            MemberAuthScreen()
        }
        composable<PricingAuthScreen> {
            PricingAuthScreen()
        }
        composable<PricingScreen> {
            PricingScreen()
        }
        composable<MemberScreen> {
            MemberScreen()
        }
    }
}