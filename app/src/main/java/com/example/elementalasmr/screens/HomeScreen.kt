package com.example.elementalasmr.screens

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.elementalasmr.models.Element
import com.example.elementalasmr.models.SampleData
import com.example.elementalasmr.models.Sound
import com.example.elementalasmr.utils.getElementColor
import com.example.elementalasmr.utils.getElementIconDrawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onElementClick: (Element) -> Unit,
    onSoundClick: (Sound) -> Unit
) {
    val elements = remember { SampleData.getAllElements() }
    val configuration = LocalConfiguration.current

    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isTablet = configuration.screenWidthDp >= 600

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("AURA")
                        Text(
                            "ASMR Sounds",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            isLandscape -> {
                LandscapeHomeLayout(
                    elements = elements,
                    onElementClick = onElementClick,
                    onSoundClick = onSoundClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            isTablet -> {
                TabletHomeLayout(
                    elements = elements,
                    onElementClick = onElementClick,
                    onSoundClick = onSoundClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            else -> {
                PortraitHomeLayout(
                    elements = elements,
                    onElementClick = onElementClick,
                    onSoundClick = onSoundClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

// ---------------- Portrait Layout ----------------

@Composable
fun PortraitHomeLayout(
    elements: List<Element>,
    onElementClick: (Element) -> Unit,
    onSoundClick: (Sound) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
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

        items(elements) { element ->
            ElementCard(
                element = element,
                onClick = { onElementClick(element) },
                onSoundClick = onSoundClick
            )
        }

        item {
            Column {
                Text(
                    text = "Recently Played",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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

// ---------------- Landscape Layout ----------------

@Composable
fun LandscapeHomeLayout(
    elements: List<Element>,
    onElementClick: (Element) -> Unit,
    onSoundClick: (Sound) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "Welcome back",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Choose your element",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }

            item {
                Text("Recently Played", style = MaterialTheme.typography.titleLarge)
            }

            items(elements.flatMap { it.sounds }.take(4)) { sound ->
                CompactSoundCard(sound = sound, onClick = { onSoundClick(sound) })
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(elements) { element ->
                CompactElementCard(
                    element = element,
                    onClick = { onElementClick(element) },
                    onSoundClick = onSoundClick
                )
            }
        }
    }
}

// ---------------- Tablet Layout ----------------

@Composable
fun TabletHomeLayout(
    elements: List<Element>,
    onElementClick: (Element) -> Unit,
    onSoundClick: (Sound) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Welcome back",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Choose your element and find peace",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.height(600.dp)
            ) {
                items(elements) { element ->
                    ElementCard(
                        element = element,
                        onClick = { onElementClick(element) },
                        onSoundClick = onSoundClick
                    )
                }
            }
        }

        item {
            Column {
                Text(
                    text = "Recently Played",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(elements.flatMap { it.sounds }.take(6)) { sound ->
                        RecentSoundCard(
                            sound = sound,
                            onClick = { onSoundClick(sound) },
                            modifier = Modifier.width(180.dp)
                        )
                    }
                }
            }
        }
    }
}

// ---------------- Cards ----------------

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
        colors = CardDefaults.cardColors(containerColor = element.primaryColor.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = getElementIconDrawable(element.type)),
                        contentDescription = element.name,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(element.name, style = MaterialTheme.typography.titleLarge, color = element.primaryColor)
                        Text(
                            element.description,
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
                    SoundListItem(sound = sound, color = element.primaryColor, onClick = { onSoundClick(sound) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun CompactElementCard(
    element: Element,
    onClick: () -> Unit,
    onSoundClick: (Sound) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = element.primaryColor.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = getElementIconDrawable(element.type)),
                contentDescription = element.name,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(element.name, style = MaterialTheme.typography.titleMedium, color = element.primaryColor)
                Text(
                    "${element.sounds.size} sounds",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun SoundListItem(sound: Sound, color: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.PlayCircle, contentDescription = "Play", tint = color, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(sound.name, style = MaterialTheme.typography.bodyLarge)
            Text(sound.duration, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
        IconButton(onClick = { /* Favorite */ }) {
            Icon(
                imageVector = if (sound.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (sound.isFavorite) color else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun RecentSoundCard(sound: Sound, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.width(140.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = getElementIconDrawable(sound.element)),
                contentDescription = sound.name,
                modifier = Modifier.size(48.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(sound.name, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            Text(sound.duration, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun CompactSoundCard(sound: Sound, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = getElementIconDrawable(sound.element)),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(sound.name, style = MaterialTheme.typography.bodyMedium)
                Text(sound.duration, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
            IconButton(onClick = onClick) {
                Icon(Icons.Default.PlayCircle, contentDescription = "Play", tint = getElementColor(sound.element))
            }
        }
    }
}

