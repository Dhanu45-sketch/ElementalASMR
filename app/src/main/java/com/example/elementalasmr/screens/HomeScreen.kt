

// screens/HomeScreen.kt
package com.example.elementalasmr.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.elementalasmr.models.Element
import com.example.elementalasmr.models.ElementType
import com.example.elementalasmr.models.SampleData
import com.example.elementalasmr.models.Sound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onElementClick: (Element) -> Unit,
    onSoundClick: (Sound) -> Unit
) {
    val elements = remember { SampleData.getAllElements() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Elemental ASMR") },
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Welcome section
            item {
                Column {
                    Text(
                        text = "Welcome back",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Choose your element and find peace",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }

            // Element cards
            items(elements) { element ->
                ElementCard(
                    element = element,
                    onClick = { onElementClick(element) },
                    onSoundClick = onSoundClick
                )
            }

            // Recently played section
            item {
                Column {
                    Text(
                        text = "Recently Played",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(elements.flatMap { it.sounds }.take(5)) { sound ->
                            RecentSoundCard(
                                sound = sound,
                                onClick = { onSoundClick(sound) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ElementCard(
    element: Element,
    onClick: () -> Unit,
    onSoundClick: (Sound) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = element.primaryColor.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = getElementIcon(element.type),
                        contentDescription = element.name,
                        tint = element.primaryColor,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = element.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = element.primaryColor
                        )
                        Text(
                            text = element.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = element.primaryColor
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = element.primaryColor.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(16.dp))

                element.sounds.forEach { sound ->
                    SoundListItem(
                        sound = sound,
                        color = element.primaryColor,
                        onClick = { onSoundClick(sound) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun SoundListItem(
    sound: Sound,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.PlayCircle,
            contentDescription = "Play",
            tint = color,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = sound.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = sound.duration,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        IconButton(onClick = { /* Add to favorites */ }) {
            Icon(
                imageVector = if (sound.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (sound.isFavorite) color else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun RecentSoundCard(
    sound: Sound,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = getElementIcon(sound.element),
                contentDescription = sound.name,
                tint = getElementColor(sound.element),
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = sound.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
            Text(
                text = sound.duration,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

fun getElementIcon(type: ElementType): androidx.compose.ui.graphics.vector.ImageVector {
    return when (type) {
        ElementType.EARTH -> Icons.Default.Terrain
        ElementType.FIRE -> Icons.Default.Whatshot
        ElementType.WATER -> Icons.Default.Waves
        ElementType.WIND -> Icons.Default.Air
    }
}

fun getElementColor(type: ElementType): Color {
    return when (type) {
        ElementType.EARTH -> Color(0xFF6B8E23)
        ElementType.FIRE -> Color(0xFFFF6347)
        ElementType.WATER -> Color(0xFF4682B4)
        ElementType.WIND -> Color(0xFF87CEEB)
    }
}