package ru.telros.telrostest.theme.models

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalBackground = compositionLocalOf<Background> { error("No Background provided") }

data class Background(
    val primary: Color,
    val secondary: Color,
    val topBar: Color,
    val buttonBlue: Color,
    val buttonRed: Color,
    val textColor: Color,
    val cardColor: Color,
)

val lightBackground = Background(
    primary = Color.White,
    topBar = Color(0xFF1A74D2),
    buttonBlue = Color(0xFF1A74D2),
    buttonRed = Color(0xFFed365b),
    textColor = Color(0xFF424242),
    cardColor = Color(0xFFfffafa),
    secondary = Color.White
)
val darkBackground = Background(
    primary = Color(0xFF424242),
    topBar = Color(0xFF1A74D2),
    buttonBlue = Color(0xFF1A74D2),
    buttonRed = Color(0xFFed365b),
    textColor = Color.White,
    cardColor = Color(0xFF5b5b5b),
    secondary = Color.White
)