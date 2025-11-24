package com.eslirodrigues.serviceappcompose.management.memberTab.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eslirodrigues.serviceappcompose.management.memberTab.model.MemberModel

@Composable
fun MemberList(
    members: List<MemberModel>,
    onEdit: (MemberModel) -> Unit,
    onDelete: (MemberModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 320.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(members) { member ->
            MemberCard(member, onEdit, onDelete)
        }
    }
}

@Composable
fun MemberCard(
    member: MemberModel,
    onEdit: (MemberModel) -> Unit,
    onDelete: (MemberModel) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = member.photo,
                    contentDescription = "Photo of ${member.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    error = rememberVectorPainter(Icons.Default.Person),
                    placeholder = rememberVectorPainter(Icons.Default.Person)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(text = member.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "ID: ${member.id}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    "Email: ${member.email}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "Birth Date: ${member.birthDate}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "Service Type: ${member.serviceType}",
                    style = MaterialTheme.typography.bodyMedium
                )
                if (member.managerId != null) {
                    Text(
                        "Manager ID: ${member.managerId}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onEdit(member) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { onDelete(member) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}