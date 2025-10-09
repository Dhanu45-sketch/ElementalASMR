// ui/theme/Color.kt (REPLACE YOUR ENTIRE FILE)
package com.example.elementalasmr.ui.theme

import androidx.compose.ui.graphics.Color

// AURA Brand Colors - Derived from the vibrant color swatch image (top to bottom)
val AuraDarkBlue = Color(0xFF25283D)        // Dark Blue - Used for Dark Mode Background
val AuraDeepRed = Color(0xFF802B48)        // Deep Berry/Burgundy
val AuraBrightRed = Color(0xFFBD4D4F)      // Muted Red/Rose
val AuraSalmon = Color(0xFFDF796B)         // Terracotta/Dusty Red
val AuraWarmOrange = Color(0xFFF1B098)     // Soft Peach/Salmon - Used for Light Mode Background

// Light theme colors - Muted Orange/Peach Background
val PrimaryLight = AuraBrightRed           // A key brand color for accents
val SecondaryLight = AuraSalmon            // Secondary accent/surface color
val TertiaryLight = AuraDeepRed            // Darker accent
val BackgroundLight = AuraWarmOrange       // Soft Peach/Salmon background
val SurfaceLight = Color(0xFFFBF5E5)        // Very light cream/off-white for cards (ensures legibility)
val OnPrimaryLight = Color(0xFFFFFFFF)     // White text on primary accent
val OnBackgroundLight = AuraDarkBlue       // Dark Blue text on light background (ensures legibility)

// Dark theme colors - Dark Blue Background
val PrimaryDark = AuraSalmon               // Terracotta accent for dark mode contrast
val SecondaryDark = AuraBrightRed          // Secondary accent
val TertiaryDark = AuraDeepRed
val BackgroundDark = AuraDarkBlue          // Deep Dark Blue background
val SurfaceDark = Color(0xFF3B4058)        // Slightly lighter dark blue for surfaces/cards
val OnPrimaryDark = AuraDarkBlue           // Dark text on bright accent
val OnBackgroundDark = Color(0xFFF1F0EB)    // Light off-white text on dark background (ensures legibility)

// Element colors (Adjusted to new palette while maintaining element identity)
val EarthPrimary = AuraDeepRed
val EarthSecondary = AuraSalmon
val FirePrimary = AuraBrightRed
val FireSecondary = AuraWarmOrange
val WaterPrimary = Color(0xFF4682B4)        // Classic Blue for Water (outside of main palette for contrast)
val WaterSecondary = Color(0xFFB0C4DE)
val WindPrimary = Color(0xFF87CEEB)         // Light Blue for Wind (outside of main palette for contrast)
val WindSecondary = Color(0xFFE0FFFF)