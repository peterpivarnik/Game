package com.example.game

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextHolder(val displayText: String,
                      val firstButtonText: String = "Späť",
                      val fontSize: TextUnit = 16.sp,
                      val oneButton: Boolean = false)
