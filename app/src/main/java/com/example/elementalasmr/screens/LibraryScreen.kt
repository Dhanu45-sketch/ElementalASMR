// screens/LibraryScreen.kt (REPLACE YOUR EXISTING LibraryScreen.kt)
package com.example.elementalasmr.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.elementalasmr.models.ElementType
import com.example.elementalasmr.models.SampleData
import com.example.elementalasmr.models.Sound
import com.example.elementalasmr.utils.getElementColor
import com.example.elementalasmr.utils.getElementIconDrawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onSoundClick: (Sound) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val elements = remember { SampleData.getAllElements() }
    val tabs = listOf("All", "Earth", "Fire", "Water", "Wind")

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isTablet = configuration.screenWidthDp >= 600

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

            when {
                isTablet -> {
                    // Tablet layout - 3 column grid
                    TabletLibraryLayout(
                        sounds = filteredSounds,
                        onSoundClick = onSoundClick
                    )
                }
                isLandscape -> {
                    // Landscape layout - 2 column grid
                    LandscapeLibraryLayout(
                        sounds = filteredSounds,
                        onSoundClick = onSoundClick
                    )
                }
                else -> {
                    // Portrait layout - single column list
                    PortraitLibraryLayout(
                        sounds = filteredSounds,
                        onSoundClick = onSoundClick
                    )
                }
            }
        }
    }
}

@Composable
fun PortraitLibraryLayout(
    sounds: List<Sound>,
    onSoundClick: (Sound) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sounds) { sound ->
            LibrarySoundCard(
                sound = sound,
                onClick = { onSoundClick(sound) }
            )
        }
    }
}

@Composable
fun LandscapeLibraryLayout(
    sounds: List<Sound>,
    onSoundClick: (Sound) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sounds) { sound ->
            CompactLibrarySoundCard(
                sound = sound,
                onClick = { onSoundClick(sound) }
            )
        }
    }
}

@Composable
fun TabletLibraryLayout(
    sounds: List<Sound>,
    onSoundClick: (Sound) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sounds) { sound ->
            CompactLibrarySoundCard(
                sound = sound,
                onClick = { onSoundClick(sound) }
            )
        }
    }
}



// LibraryScreen.kt - Update the icon usages

// In LibrarySoundCard, change the Icon to Image:
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
            // Element icon - CHANGED to Image
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(8.dp),
                color = getElementColor(sound.element).copy(alpha = 0.2f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = getElementIconDrawable(sound.element)),
                        contentDescription = sound.element.name,
                        modifier = Modifier.size(36.dp)
                    )
                }
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

// In CompactLibrarySoundCard, change the Icon to Image:
@Composable
fun CompactLibrarySoundCard(
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Element icon - CHANGED to Image
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp),
                color = getElementColor(sound.element).copy(alpha = 0.2f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = getElementIconDrawable(sound.element)),
                        contentDescription = sound.element.name,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sound info
            Text(
                text = sound.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = sound.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Duration",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = sound.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }

                IconButton(
                    onClick = onClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircle,
                        contentDescription = "Play",
                        tint = getElementColor(sound.element),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}