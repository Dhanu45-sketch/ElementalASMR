package com.example.elementalasmr.utils

import androidx.compose.ui.graphics.Color
import com.example.elementalasmr.R
import com.example.elementalasmr.models.ElementType

fun getElementIconDrawable(type: ElementType): Int {
    return when (type) {
        ElementType.EARTH -> R.drawable.element_earth
        ElementType.FIRE -> R.drawable.element_fire
        ElementType.WATER -> R.drawable.element_water
        ElementType.WIND -> R.drawable.element_wind
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