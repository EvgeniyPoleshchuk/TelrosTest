package ru.telros.telrostest.theme.models

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


val LocalTypography = compositionLocalOf<Typography> { error("No Typography provided") }

data class Typography(
    val title: TextStyle,
    val textSize: TextStyle,
    val header: TextStyle,
)

val compactSmallTypography = Typography(
    title = TextStyle(
        fontSize = 12.sp
    ),
    textSize = TextStyle(
        fontSize = 10.sp
    ),
    header = TextStyle(
        fontSize = 15.sp
    )
)
val compactMediumTypography = Typography(
    title = TextStyle(
        fontSize = 18.sp
    ),
    textSize = TextStyle(
        fontSize = 15.sp
    )
    ,
    header = TextStyle(
        fontSize = 22.sp
    )

)
val mediumTypography = Typography(
    title = TextStyle(
        fontSize = 26.sp
    ),
    textSize = TextStyle(
        fontSize = 20.sp
    ),
    header = TextStyle(
        fontSize = 30.sp
    )

)
val expandedTypography = Typography(
    title = TextStyle(
        fontSize = 32.sp
    ),
    textSize = TextStyle(
        fontSize = 25.sp
    ),
    header = TextStyle(
        fontSize = 36.sp
    )
)

