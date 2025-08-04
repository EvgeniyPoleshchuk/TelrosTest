package ru.telros.telrostest.presentation.generation.ui_components.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun SymbolPage(
    onAddChar: (Char) -> Unit
) {
    val symbols = listOf(
        '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.',
        '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`',
        '{', '|', '}', '~'
    )
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(dimens.medium),
        verticalArrangement = Arrangement.spacedBy(dimens.small),
    ) {
        items(symbols, key = { it }) {
            Text(
                "$it",
                modifier = Modifier.fillMaxWidth()
                    .clickable{
                        onAddChar(it)
                    },
                style = typography.header,
                color = background.textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}