package ru.telros.telrostest.theme.models

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimens = compositionLocalOf<Dimens> { error("No Dimens provided") }

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small: Dp = 0.dp,
    val medium: Dp = 0.dp,
    val large: Dp = 0.dp,
    val extraLarge: Dp = 0.dp
)

val CompactSmallDimens = Dimens(
    extraSmall = 2.dp,
    small = 4.dp,
    medium = 8.dp,
    large = 16.dp,
    extraLarge = 32.dp
)
val CompactMediumDimens = Dimens(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    extraLarge = 64.dp
)
val MediumDimens = Dimens(
    extraSmall = 8.dp,
    small = 16.dp,
    medium = 32.dp,
    large = 64.dp,
    extraLarge = 128.dp
)
val ExpandedDimens = Dimens(
    extraSmall = 16.dp,
    small = 32.dp,
    medium = 64.dp,
    large = 128.dp,
    extraLarge = 256.dp


)