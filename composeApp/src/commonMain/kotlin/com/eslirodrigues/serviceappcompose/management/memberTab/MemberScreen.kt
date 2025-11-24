package com.eslirodrigues.serviceappcompose.management.memberTab

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eslirodrigues.serviceappcompose.management.memberTab.components.DeleteMemberDialog
import com.eslirodrigues.serviceappcompose.management.memberTab.components.MemberFormDialog
import com.eslirodrigues.serviceappcompose.management.memberTab.components.MemberList
import com.eslirodrigues.serviceappcompose.management.memberTab.model.MemberModel


@Composable
fun MemberScreen(
    viewModel: MemberViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var showFormDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedMember by remember { mutableStateOf<MemberModel?>(null) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    selectedMember = null
                    showFormDialog = true
                },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                text = { Text("Create Member") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Members",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when (val state = uiState) {
                is MemberUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is MemberUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                is MemberUiState.Loaded -> {
                    MemberList(
                        members = state.members,
                        onEdit = { member ->
                            selectedMember = member
                            showFormDialog = true
                        },
                        onDelete = { member ->
                            selectedMember = member
                            showDeleteDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showFormDialog) {
        MemberFormDialog(
            member = selectedMember,
            onDismiss = { showFormDialog = false },
            onSave = { member ->
                viewModel.saveMember(member, isEdit = selectedMember != null)
                showFormDialog = false
            }
        )
    }

    if (showDeleteDialog && selectedMember != null) {
        DeleteMemberDialog(
            member = selectedMember!!,
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                viewModel.deleteMember(selectedMember!!.id)
                showDeleteDialog = false
            }
        )
    }
}


