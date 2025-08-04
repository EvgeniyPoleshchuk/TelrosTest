package ru.telros.telrostest.presentation.splash.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: Int) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = title),
                style = typography.header,
                textAlign = TextAlign.Center,
                color = background.secondary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = background.buttonBlue,
        )
    )
}