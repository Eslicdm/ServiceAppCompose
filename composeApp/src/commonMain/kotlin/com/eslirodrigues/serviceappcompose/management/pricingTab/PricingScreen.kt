package com.eslirodrigues.serviceappcompose.management.pricingTab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.serviceappcompose.management.pricingTab.components.PricingFormDialog
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceModel

@Composable
fun PricingScreen(
    viewModel: PricingViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedPriceForEdit by remember { mutableStateOf<PriceModel?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is PricingState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is PricingState.Error -> {
                Text(
                    text = currentState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is PricingState.Loaded -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 320.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(currentState.prices) { price ->
                        PricingCard(
                            price = price,
                            onEditClick = { selectedPriceForEdit = price }
                        )
                    }
                }
            }
        }

        selectedPriceForEdit?.let { price ->
            PricingFormDialog(
                price = price,
                onDismiss = { selectedPriceForEdit = null },
                onConfirm = { updateDto ->
                    viewModel.updatePrice(price.priceType, updateDto)
                    selectedPriceForEdit = null
                }
            )
        }
    }
}

@Composable
fun PricingCard(
    price: PriceModel,
    onEditClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = price.priceType.toTitleCase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = price.value.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Content
            Text(
                text = price.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Metadata
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Created: ${price.createdAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Updated: ${price.updatedAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Price")
                }
            }
        }
    }
}
