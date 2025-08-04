package ru.telros.telrostest.theme

import androidx.compose.runtime.Composable
import ru.telros.telrostest.theme.models.LocalBackground
import ru.telros.telrostest.theme.models.LocalDimens
import ru.telros.telrostest.theme.models.LocalTypography

object Resources {

    val background
        @Composable get() = LocalBackground.current

    val dimens
        @Composable get() = LocalDimens.current

    val typography
        @Composable get() = LocalTypography.current
}