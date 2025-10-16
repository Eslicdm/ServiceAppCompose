package com.eslirodrigues.serviceappcompose.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LandingScreen(
    onNavToMemberClick: () -> Unit,
    onNavToPricingClick: () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues.calculateTopPadding())) {
            Text("Landing Screen")
            Button(onClick = onNavToMemberClick) {
                Text("Member Login")
            }
            Button(onClick = onNavToPricingClick) {
                Text("Pricing Login")
            }
        }
    }
}