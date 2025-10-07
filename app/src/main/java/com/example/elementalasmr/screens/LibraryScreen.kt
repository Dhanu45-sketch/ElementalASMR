

// screens/LibraryScreen.kt
package com.example.elementalasmr.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.elementalasmr.models.ElementType
import com.example.elementalasmr.models.SampleData
import com.example.elementalasmr.models.Sound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onSoundClick: (Sound) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val elements = remember { SampleData.getAllElements() }
    val tabs = listOf("All", "Earth", "Fire", "Water", "Wind")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sound Library") },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tab Row for filtering by element
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 16.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            // Sound list based on selected tab
            val filteredSounds = when (selectedTab) {
                0 -> elements.flatMap { it.sounds } // All
                1 -> elements.find { it.type == ElementType.EARTH }?.sounds ?: emptyList()
                2 -> elements.find { it.type == ElementType.FIRE }?.sounds ?: emptyList()
                3 -> elements.find { it.type == ElementType.WATER }?.sounds ?: emptyList()
                4 -> elements.find { it.type == ElementType.WIND }?.sounds ?: emptyList()
                else -> emptyList()
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredSounds) { sound ->
                    LibrarySoundCard(
                        sound = sound,
                        onClick = { onSoundClick(sound) }
                    )
                }
            }
        }
    }
}

@Composable
fun LibrarySoundCard(
    sound: Sound,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Element icon
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(8.dp),
                color = getElementColor(sound.element).copy(alpha = 0.2f)
            ) {
                Icon(
                    imageVector = getElementIcon(sound.element),
                    contentDescription = sound.element.name,
                    tint = getElementColor(sound.element),
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Sound info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = sound.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = sound.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Duration",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = sound.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            // Play button
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "Play",
                    tint = getElementColor(sound.element),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}