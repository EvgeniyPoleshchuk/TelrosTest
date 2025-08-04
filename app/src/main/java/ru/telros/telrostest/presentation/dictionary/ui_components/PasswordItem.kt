package ru.telros.telrostest.presentation.dictionary.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.telros.telrostest.R
import ru.telros.telrostest.presentation.dictionary.PasswordListItem
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun PasswordItem(
    passwordItem: PasswordListItem.PasswordItem,
    onCopy: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = dimens.extraSmall),
        shape = RoundedCornerShape(dimens.small),
        colors = CardDefaults.cardColors(
            containerColor = background.cardColor
        )

    ) {
        Column(
            modifier = Modifier.padding(dimens.medium),
            verticalArrangement = Arrangement.spacedBy(dimens.small)
        ) {
            Text(
                text = "${stringResource(R.string.password)}: ${passwordItem.password}",
                style = typography.title,
                color = background.textColor
            )
            if (passwordItem.isGenerated) {
                Text(
                    text = "${stringResource(R.string.entropy)}: ${passwordItem.entropy}",
                    style = typography.title,
                    color = background.textColor
                )
                Text(
                    text = "${stringResource(R.string.symbols)}: ${passwordItem.charSet ?: "-"}",
                    style = typography.title,
                    color = background.textColor
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimens.small)
            ) {
                Button(
                    onClick = onCopy,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(dimens.small),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = background.buttonBlue
                    )
                ) {
                    Text(stringResource(R.string.copy),
                        color = background.primary,
                        style = typography.title)
                }
                Button(
                    onClick = onDelete,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(dimens.small),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = background.buttonRed
                    )
                ) {
                    Text(
                        stringResource(R.string.delete),
                            color = background.primary,
                            style = typography.title
                        )
                }
            }
        }
    }
}