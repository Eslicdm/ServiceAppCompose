package com.eslirodrigues.serviceappcompose.management

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eslirodrigues.serviceappcompose.management.memberTab.MemberScreen
import com.eslirodrigues.serviceappcompose.management.memberTab.MemberViewModel
import com.eslirodrigues.serviceappcompose.management.pricingTab.PricingScreen
import com.eslirodrigues.serviceappcompose.management.pricingTab.PricingViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ManagementScreen(
    onLogout: () -> Unit
) {
    val memberViewModel = koinViewModel<MemberViewModel>()
    val pricingViewModel = koinViewModel<PricingViewModel>()
    val tabs = listOf("Members", "Pricing")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Management",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout")
                }
            }

            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                when (selectedTabIndex) {
                    0 -> MemberScreen(viewModel = memberViewModel)
                    1 -> PricingScreen(viewModel = pricingViewModel)
                }
            }
        }
    }
}
