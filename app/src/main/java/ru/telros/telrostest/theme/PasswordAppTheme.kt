package ru.telros.telrostest.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import ru.telros.telrostest.theme.models.Background
import ru.telros.telrostest.theme.models.CompactMediumDimens
import ru.telros.telrostest.theme.models.CompactSmallDimens
import ru.telros.telrostest.theme.models.ExpandedDimens
import ru.telros.telrostest.theme.models.LocalBackground
import ru.telros.telrostest.theme.models.LocalDimens
import ru.telros.telrostest.theme.models.LocalTypography
import ru.telros.telrostest.theme.models.MediumDimens
import ru.telros.telrostest.theme.models.compactMediumTypography
import ru.telros.telrostest.theme.models.compactSmallTypography
import ru.telros.telrostest.theme.models.darkBackground
import ru.telros.telrostest.theme.models.expandedTypography
import ru.telros.telrostest.theme.models.lightBackground
import ru.telros.telrostest.theme.models.mediumTypography

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PasswordAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val background: Background

    if (darkTheme) {
        background = darkBackground
    } else {
        background = lightBackground
    }
    val activity = LocalActivity.current
    val window = activity?.let { calculateWindowSizeClass(it) }
    val config = LocalConfiguration.current
    var typography = compactSmallTypography
    var dimens = CompactSmallDimens

    when (window?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                dimens = CompactSmallDimens
                typography = compactSmallTypography
            } else if (config.screenWidthDp <= 599) {
                dimens = CompactMediumDimens
                typography = compactMediumTypography
            } else {
                dimens = MediumDimens
                typography = compactMediumTypography

            }
        }

        WindowWidthSizeClass.Medium -> {
            dimens = MediumDimens
            typography = mediumTypography
        }

        else -> {
            dimens = ExpandedDimens
            typography = expandedTypography
        }

    }

    CompositionLocalProvider(
        LocalBackground provides background,
        LocalDimens provides dimens,
        LocalTypography provides typography
    ) {
        MaterialTheme(colorScheme = colorScheme) {
            content()

        }
    }

}