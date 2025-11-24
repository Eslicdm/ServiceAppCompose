package com.eslirodrigues.serviceappcompose.management.memberTab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.eslirodrigues.serviceappcompose.management.memberTab.model.MemberModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberFormDialog(
    member: MemberModel?,
    onDismiss: () -> Unit,
    onSave: (MemberModel) -> Unit
) {
    val serviceTypes = listOf("Free", "Half-Price", "Full-Price")

    var name by remember { mutableStateOf(member?.name ?: "") }
    var email by remember { mutableStateOf(member?.email ?: "") }
    var birthDate by remember { mutableStateOf(member?.birthDate ?: "") }
    var serviceType by remember { mutableStateOf(member?.serviceType ?: "") }

    val isFormValid = name.isNotBlank() && email.contains("@") &&
            birthDate.isNotBlank() && serviceType.isNotBlank()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = if (member != null) "Edit Member" else "Create New Member",
                    style = MaterialTheme.typography.headlineSmall
                )

                AsyncImage(
                    model = member?.photo,
                    contentDescription = "Photo of ${member?.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    error = rememberVectorPainter(Icons.Default.Person),
                    placeholder = rememberVectorPainter(Icons.Default.Person)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                FormDatePicker(
                    dateStr = birthDate,
                    onDateSelected = { birthDate = it }
                )

                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = serviceType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Service Type") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        serviceTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    serviceType = type
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val photo = member?.photo
                                ?: "https://ui-avatars.com/api/?name=$name&background=random"
                            val newMember = MemberModel(
                                name = name,
                                email = email,
                                birthDate = birthDate,
                                serviceType = serviceType,
                                photo = photo,
                            )
                            onSave(newMember)
                        },
                        enabled = isFormValid
                    ) {
                        Text(if (member != null) "Save Changes" else "Create")
                    }
                }
            }
        }
    }
}