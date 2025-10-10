package com.example.elementalasmr.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var userName by remember { mutableStateOf("John Doe") }
    var userEmail by remember { mutableStateOf("john.doe@example.com") }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoPlayEnabled by remember { mutableStateOf(false) }
    var downloadQuality by remember { mutableStateOf("High") }
    var sleepTimerDefault by remember { mutableStateOf("30") }
    var expanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isTablet = configuration.screenWidthDp >= 600

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile & Settings") }
            )
        }
    ) { paddingValues ->
        when {
            isLandscape -> {
                LandscapeProfileLayout(
                    userName = userName,
                    userEmail = userEmail,
                    onNameChange = { userName = it },
                    onEmailChange = { userEmail = it },
                    notificationsEnabled = notificationsEnabled,
                    onNotificationsChange = { notificationsEnabled = it },
                    autoPlayEnabled = autoPlayEnabled,
                    onAutoPlayChange = { autoPlayEnabled = it },
                    downloadQuality = downloadQuality,
                    onQualityChange = { downloadQuality = it },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    sleepTimerDefault = sleepTimerDefault,
                    onTimerChange = { sleepTimerDefault = it },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            isTablet -> {
                TabletProfileLayout(
                    userName = userName,
                    userEmail = userEmail,
                    onNameChange = { userName = it },
                    onEmailChange = { userEmail = it },
                    notificationsEnabled = notificationsEnabled,
                    onNotificationsChange = { notificationsEnabled = it },
                    autoPlayEnabled = autoPlayEnabled,
                    onAutoPlayChange = { autoPlayEnabled = it },
                    downloadQuality = downloadQuality,
                    onQualityChange = { downloadQuality = it },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    sleepTimerDefault = sleepTimerDefault,
                    onTimerChange = { sleepTimerDefault = it },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            else -> {
                PortraitProfileLayout(
                    userName = userName,
                    userEmail = userEmail,
                    onNameChange = { userName = it },
                    onEmailChange = { userEmail = it },
                    notificationsEnabled = notificationsEnabled,
                    onNotificationsChange = { notificationsEnabled = it },
                    autoPlayEnabled = autoPlayEnabled,
                    onAutoPlayChange = { autoPlayEnabled = it },
                    downloadQuality = downloadQuality,
                    onQualityChange = { downloadQuality = it },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    sleepTimerDefault = sleepTimerDefault,
                    onTimerChange = { sleepTimerDefault = it },
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun PortraitProfileLayout(
    userName: String,
    userEmail: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    autoPlayEnabled: Boolean,
    onAutoPlayChange: (Boolean) -> Unit,
    downloadQuality: String,
    onQualityChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    sleepTimerDefault: String,
    onTimerChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ProfileHeader()

        Spacer(modifier = Modifier.height(24.dp))

        AccountInfoCard(
            userName = userName,
            userEmail = userEmail,
            onNameChange = onNameChange,
            onEmailChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppSettingsCard(
            notificationsEnabled = notificationsEnabled,
            onNotificationsChange = onNotificationsChange,
            autoPlayEnabled = autoPlayEnabled,
            onAutoPlayChange = onAutoPlayChange,
            downloadQuality = downloadQuality,
            onQualityChange = onQualityChange,
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            sleepTimerDefault = sleepTimerDefault,
            onTimerChange = onTimerChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        StatisticsCard()

        Spacer(modifier = Modifier.height(24.dp))

        LogoutButton()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun LandscapeProfileLayout(
    userName: String,
    userEmail: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    autoPlayEnabled: Boolean,
    onAutoPlayChange: (Boolean) -> Unit,
    downloadQuality: String,
    onQualityChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    sleepTimerDefault: String,
    onTimerChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Left column
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()

            Spacer(modifier = Modifier.height(16.dp))

            AccountInfoCard(
                userName = userName,
                userEmail = userEmail,
                onNameChange = onNameChange,
                onEmailChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            StatisticsCard()

            Spacer(modifier = Modifier.height(16.dp))

            LogoutButton()
        }

        // Right column
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            AppSettingsCard(
                notificationsEnabled = notificationsEnabled,
                onNotificationsChange = onNotificationsChange,
                autoPlayEnabled = autoPlayEnabled,
                onAutoPlayChange = onAutoPlayChange,
                downloadQuality = downloadQuality,
                onQualityChange = onQualityChange,
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                sleepTimerDefault = sleepTimerDefault,
                onTimerChange = onTimerChange
            )
        }
    }
}

@Composable
fun TabletProfileLayout(
    userName: String,
    userEmail: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    autoPlayEnabled: Boolean,
    onAutoPlayChange: (Boolean) -> Unit,
    downloadQuality: String,
    onQualityChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    sleepTimerDefault: String,
    onTimerChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Left side - Profile & Account
        Column(
            modifier = Modifier
                .weight(0.4f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()

            Spacer(modifier = Modifier.height(24.dp))

            AccountInfoCard(
                userName = userName,
                userEmail = userEmail,
                onNameChange = onNameChange,
                onEmailChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            StatisticsCard()

            Spacer(modifier = Modifier.height(24.dp))

            LogoutButton()
        }

        // Right side - Settings
        Column(
            modifier = Modifier
                .weight(0.6f)
                .verticalScroll(rememberScrollState())
        ) {
            AppSettingsCard(
                notificationsEnabled = notificationsEnabled,
                onNotificationsChange = onNotificationsChange,
                autoPlayEnabled = autoPlayEnabled,
                onAutoPlayChange = onAutoPlayChange,
                downloadQuality = downloadQuality,
                onQualityChange = onQualityChange,
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                sleepTimerDefault = sleepTimerDefault,
                onTimerChange = onTimerChange
            )
        }
    }
}

@Composable
fun ProfileHeader() {
    Surface(
        modifier = Modifier.size(100.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    TextButton(onClick = { /* Change photo */ }) {
        Text("Change Photo")
    }
}

@Composable
fun AccountInfoCard(
    userName: String,
    userEmail: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Account Information",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = onNameChange,
                label = { Text("Full Name") },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = userEmail,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Save changes */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSettingsCard(
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    autoPlayEnabled: Boolean,
    onAutoPlayChange: (Boolean) -> Unit,
    downloadQuality: String,
    onQualityChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    sleepTimerDefault: String,
    onTimerChange: (String) -> Unit
) {
    val qualityOptions = listOf("Low", "Medium", "High")
    val timerOptions = listOf("15", "30", "45", "60")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "App Settings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingToggle(
                icon = Icons.Default.Notifications,
                title = "Push Notifications",
                description = "Receive meditation reminders",
                checked = notificationsEnabled,
                onCheckedChange = onNotificationsChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            SettingToggle(
                icon = Icons.Default.PlayCircle,
                title = "Auto-play Similar Sounds",
                description = "Continue with similar content",
                checked = autoPlayEnabled,
                onCheckedChange = onAutoPlayChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.HighQuality,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Download Quality",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = onExpandedChange
                ) {
                    OutlinedTextField(
                        value = downloadQuality,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { onExpandedChange(false) }
                    ) {
                        qualityOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    onQualityChange(option)
                                    onExpandedChange(false)
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Timer,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Default Sleep Timer",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    timerOptions.forEach { timer ->
                        FilterChip(
                            selected = sleepTimerDefault == timer,
                            onClick = { onTimerChange(timer) },
                            label = { Text("$timer min") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingToggle(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun StatisticsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Your Statistics",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "Total Hours", value = "24.5")
                StatItem(label = "Sessions", value = "47")
                StatItem(label = "Streak", value = "7 days")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun LogoutButton() {
    OutlinedButton(
        onClick = { /* Logout */ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.error
        )
    ) {
        Icon(Icons.Default.Logout, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Logout")
    }
}