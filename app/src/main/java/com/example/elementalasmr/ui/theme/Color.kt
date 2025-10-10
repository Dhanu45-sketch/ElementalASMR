// ui/theme/Color.kt
package com.example.elementalasmr.ui.theme

import androidx.compose.ui.graphics.Color

// --- Core Palette ---
val AuraMidnight = Color(0xFF2E3147)       // Muted indigo background (matches your logo backdrop)
val AuraSlate = Color(0xFF3E415C)          // Slightly lighter indigo-gray for surfaces
val AuraMistBlue = Color(0xFFD7E2EE)       // Pastel sky blue (light theme background)
val AuraCoolLilac = Color(0xFFC7C9E2)      // Gentle lavender tone
val AuraTealGray = Color(0xFF9BB6B3)       // Calm desaturated teal
val AuraTerracotta = Color(0xFFCB6454)     // logo’s terracotta/orange accent
val AuraSoftCoral = Color(0xFFEB8669)      // Softened coral tone for highlights
val AuraCreamWhite = Color(0xFFF9F7F4)     // Warm off-white for text and light surfaces

// --- Light Theme ---
val PrimaryLight = AuraTerracotta          // Accent ties in logo warmth
val SecondaryLight = AuraCoolLilac         // Secondary calm pastel
val TertiaryLight = AuraTealGray           // Subtle contrast tone
val BackgroundLight = AuraMistBlue         // Cool pastel background
val SurfaceLight = AuraCreamWhite          // Light surface/cards
val OnPrimaryLight = Color(0xFFFFFFFF)     // White text on accent
val OnBackgroundLight = AuraMidnight       // Dark text for readability

// --- Dark Theme ---
val PrimaryDark = AuraSoftCoral            // Warm accent pops on dark background
val SecondaryDark = AuraTealGray           // Cool counterbalance tone
val TertiaryDark = AuraCoolLilac
val BackgroundDark = AuraMidnight          // Deep indigo base
val SurfaceDark = AuraSlate                // Lighter indigo for cards
val OnPrimaryDark = AuraMidnight           // Dark text on light accent
val OnBackgroundDark = AuraCreamWhite      // Light text on dark background

// --- Element Colors  ---
val EarthPrimary = Color(0xFF7A6F63)       // Muted stone brown
val EarthSecondary = Color(0xFFB8A89A)
val FirePrimary = AuraTerracotta           // Warm, but controlled
val FireSecondary = AuraSoftCoral
val WaterPrimary = Color(0xFF6BAFD6)       // Calm ocean blue
val WaterSecondary = Color(0xFFAED4E8)
val WindPrimary = Color(0xFF9DDAD1)        // Breezy pastel teal
val WindSecondary = Color(0xFFD8F3EE)
