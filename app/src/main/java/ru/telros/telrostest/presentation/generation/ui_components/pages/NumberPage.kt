package ru.telros.telrostest.presentation.generation.ui_components.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun NumbersPage(
    onAddChar: (Char) -> Unit
) {
    val numbers = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(dimens.medium),
        verticalArrangement = Arrangement.spacedBy(dimens.small),
    ) {
        items(numbers) {
            Text(
                "$it",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onAddChar(it)
                    },
                style = typography.header,
                color = background.textColor,
                textAlign = TextAlign.Center
            )
        }

    }
}