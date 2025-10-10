
package com.example.elementalasmr.models

import androidx.compose.ui.graphics.Color

enum class ElementType {
    EARTH, FIRE, WATER, WIND
}

data class Element(
    val type: ElementType,
    val name: String,
    val description: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val sounds: List<Sound>
)

data class Sound(
    val id: String,
    val name: String,
    val description: String,
    val duration: String,
    val fileName: String, // Placeholder-file not added yet
    val element: ElementType,
    val isFavorite: Boolean = false
)

data class MeditationSession(
    val id: String,
    val title: String,
    val description: String,
    val duration: String,
    val difficulty: String, // Beginner, Intermediate, Advanced
    val imagePlaceholder: String
)

// Sample data
object SampleData {
    val earthSounds = listOf(
        Sound("e1", "Forest Rain", "Gentle rainfall in a lush forest", "45:00", "forest_rain.mp3", ElementType.EARTH),
        Sound("e2", "Mountain Stream", "Babbling brook in the mountains", "60:00", "mountain_stream.mp3", ElementType.EARTH),
        Sound("e3", "Cave Ambience", "Deep cave echoes and drips", "30:00", "cave_ambience.mp3", ElementType.EARTH),
        Sound("e4", "Garden Birds", "Morning birds chirping", "40:00", "garden_birds.mp3", ElementType.EARTH)
    )

    val fireSounds = listOf(
        Sound("f1", "Crackling Fireplace", "Warm fireplace crackling", "60:00", "fireplace.mp3", ElementType.FIRE),
        Sound("f2", "Bonfire Night", "Outdoor bonfire sounds", "45:00", "bonfire.mp3", ElementType.FIRE),
        Sound("f3", "Candle Flame", "Gentle candle flickering", "30:00", "candle.mp3", ElementType.FIRE),
        Sound("f4", "Desert Heat", "Warm desert ambience", "50:00", "desert_heat.mp3", ElementType.FIRE)
    )

    val waterSounds = listOf(
        Sound("w1", "Ocean Waves", "Rhythmic ocean waves on shore", "60:00", "ocean_waves.mp3", ElementType.WATER),
        Sound("w2", "Underwater", "Submerged water sounds", "45:00", "underwater.mp3", ElementType.WATER),
        Sound("w3", "Rain on Window", "Rain tapping on glass", "40:00", "rain_window.mp3", ElementType.WATER),
        Sound("w4", "Waterfall", "Powerful waterfall cascade", "50:00", "waterfall.mp3", ElementType.WATER)
    )

    val windSounds = listOf(
        Sound("wi1", "Gentle Breeze", "Soft wind through trees", "45:00", "gentle_breeze.mp3", ElementType.WIND),
        Sound("wi2", "Mountain Wind", "Strong mountain gusts", "40:00", "mountain_wind.mp3", ElementType.WIND),
        Sound("wi3", "Wind Chimes", "Peaceful chimes in breeze", "35:00", "wind_chimes.mp3", ElementType.WIND),
        Sound("wi4", "Storm Winds", "Powerful storm ambience", "50:00", "storm_winds.mp3", ElementType.WIND)
    )

    val meditationSessions = listOf(
        MeditationSession("m1", "Morning Grounding", "Start your day centered and calm", "10 min", "Beginner", "morning"),
        MeditationSession("m2", "Deep Relaxation", "Release tension and stress", "20 min", "Intermediate", "relax"),
        MeditationSession("m3", "Sleep Preparation", "Prepare mind and body for rest", "15 min", "Beginner", "sleep"),
        MeditationSession("m4", "Breath Awareness", "Focus on natural breathing", "12 min", "Intermediate", "breath"),
        MeditationSession("m5", "Body Scan", "Progressive relaxation technique", "25 min", "Advanced", "body")
    )

    fun getAllElements(): List<Element> = listOf(
        Element(
            ElementType.EARTH,
            "Earth",
            "Grounding sounds from nature",
            Color(0xFF6B8E23),  // Olive green
            Color(0xFF8FBC8F),  // Dark sea green
            earthSounds
        ),
        Element(
            ElementType.FIRE,
            "Fire",
            "Warm and energizing sounds",
            Color(0xFFFF6347),  // Tomato red
            Color(0xFFFF8C00),  // Dark orange
            fireSounds
        ),
        Element(
            ElementType.WATER,
            "Water",
            "Flowing and calming sounds",
            Color(0xFF4682B4),  // Steel blue
            Color(0xFF5F9EA0),  // Cadet blue
            waterSounds
        ),
        Element(
            ElementType.WIND,
            "Wind",
            "Airy and refreshing sounds",
            Color(0xFF87CEEB),  // Sky blue
            Color(0xFFB0C4DE),  // Light steel blue
            windSounds
        )
    )
}