package com.eslirodrigues.serviceappcompose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.serviceappcompose.navigation.ServiceAppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()
    AppTheme {
        ServiceAppNavGraph(navController)
    }
    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }
}