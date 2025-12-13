package com.eslirodrigues.serviceappcompose.management.pricingTab.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceModel
import com.eslirodrigues.serviceappcompose.management.pricingTab.model.PriceUpdateDto

@Composable
fun PricingFormDialog(
    price: PriceModel,
    onDismiss: () -> Unit,
    onConfirm: (PriceUpdateDto) -> Unit
) {
    var valueStr by remember { mutableStateOf(price.value.toString()) }
    var description by remember { mutableStateOf(price.description) }
    var valueError by remember { mutableStateOf<String?>(null) }
    var descError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        var isValid = true
        val doubleVal = valueStr.toDoubleOrNull()

        if (doubleVal == null || doubleVal < 0) {
            valueError = "Value must be positive"
            isValid = false
        } else {
            valueError = null
        }

        if (description.isBlank()) {
            descError = "Description is required"
            isValid = false
        } else {
            descError = null
        }
        return isValid
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit ${price.priceType.toTitleCase()} Price") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = valueStr,
                    onValueChange = { valueStr = it },
                    label = { Text("Value") },
                    isError = valueError != null,
                    supportingText = { valueError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    isError = descError != null,
                    supportingText = { descError?.let { Text(it) } },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (validate()) {
                        onConfirm(
                            PriceUpdateDto(
                                value = valueStr.toDouble(),
                                description = description
                            )
                        )
                    }
                }
            ) {
                Text("Save Changes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
