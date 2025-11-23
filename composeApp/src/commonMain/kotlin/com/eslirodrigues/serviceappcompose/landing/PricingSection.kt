package com.eslirodrigues.serviceappcompose.landing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

@Composable
fun PricingSection() {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isWideScreen =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    val plans = remember {
        listOf(
            Triple("Free", "$0/month", listOf("1GB Storage")),
            Triple("Half Price", "$50/month", listOf("7GB Storage")),
            Triple("Full Price", "$100/month", listOf("15GB Storage"))
        )
    }

    if (isWideScreen) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
        ) {
            plans.forEachIndexed { index, (title, price, features) ->
                if (index > 0) Spacer(modifier = Modifier.width(20.dp))

                Box(modifier = Modifier.width(300.dp)) {
                    PricingCard(title, price, features)
                }
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            plans.forEach { (title, price, features) ->
                PricingCard(title, price, features)
            }
        }
    }
}


@Composable
fun PricingCard(title: String, price: String, features: List<String>) {
    Card(
        modifier = Modifier.widthIn(max = 300.dp).fillMaxWidth(),
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(text = price, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            features.forEach { feature ->
                Text(text = "• $feature", modifier = Modifier.padding(bottom = 4.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Buy Action */ }) {
                Text("Buy")
            }
        }
    }
}