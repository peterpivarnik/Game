package com.example.game

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.game.ComponentType.TWO_BUTTONS

data class DataHolder(val displayText: String,
                      val firstButtonText: String = "Späť",
                      val fontSize: TextUnit = 16.sp,
                      val componentType: ComponentType = TWO_BUTTONS)
