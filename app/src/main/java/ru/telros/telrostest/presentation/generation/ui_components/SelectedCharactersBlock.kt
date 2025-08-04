package ru.telros.telrostest.presentation.generation.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.telros.telrostest.R
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun SelectedCharactersBlock(
    selectedChars: List<Char>
) {
    val digitList = selectedChars.filter { it.isDigit() }
    val letterList = selectedChars.filter { it.isLetter() }
    val specialList = selectedChars.filter { !it.isLetterOrDigit() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimens.medium)
    ) {
        Text(
            stringResource(R.string.selectedSymbols),
            style = typography.header,
            color = background.textColor
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens.medium),
            colors = CardDefaults.cardColors(
                containerColor = background.cardColor
            )
        ) {
            Column(modifier = Modifier.padding(dimens.medium)) {
                Text(
                    "${stringResource(R.string.letters)}: ${if (letterList.isNotEmpty()) letterList.joinToString() else ""}",
                    style = typography.textSize,
                    color = background.textColor
                )
                Text(
                    "${stringResource(R.string.numbers)}: ${if (digitList.isNotEmpty()) digitList.joinToString() else ""}",
                    style = typography.textSize,
                    color = background.textColor
                )
                Text(
                    "${stringResource(R.string.SpecialSymbol)}: ${if (specialList.isNotEmpty()) specialList.joinToString() else ""}",
                    style = typography.textSize,
                    color = background.textColor
                )
            }
        }
    }
}