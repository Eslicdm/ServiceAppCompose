package com.eslirodrigues.serviceappcompose.pricing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PricingScreen() {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues.calculateTopPadding())) {
            Text("Pricing Screen")
        }
    }
}