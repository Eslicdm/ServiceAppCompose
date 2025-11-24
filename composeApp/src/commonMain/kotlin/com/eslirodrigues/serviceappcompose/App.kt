package com.eslirodrigues.serviceappcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.serviceappcompose.di.appModule
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()
    KoinApplication(application = {
        modules(appModule)
    }) {
        AppTheme {
            ServiceAppNavGraph(navController)
        }
        LaunchedEffect(navController) {
            onNavHostReady(navController)
        }
    }
}