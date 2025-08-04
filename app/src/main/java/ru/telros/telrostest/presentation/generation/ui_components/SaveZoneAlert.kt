package ru.telros.telrostest.presentation.generation.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.telros.telrostest.R
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.typography
import ru.telros.telrostest.utils.PasswordType

@Composable
fun SaveZoneAlert(
    onDismiss: () -> Unit,
    onSave: (PasswordType) -> Unit,
) {
    var category by remember { mutableStateOf(PasswordType.LOADED) }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = background.cardColor,
        confirmButton = {
            ActionButton(R.string.save, Modifier) {
                onSave(category)
            }
        },
        dismissButton = {
            ActionButton(R.string.cancel, Modifier) {
                onDismiss()
            }
        },
        title = {
            Column {
                Text(
                    stringResource(R.string.selectSavePlace),
                    style = typography.header,
                    color = background.textColor,
                )

            }
        },
        text = {
            CategoryDropDown {
                category = it
            }
        }

    )
}

@Composable
fun CategoryDropDown(selectedCategory: (PasswordType) -> Unit) {
    var expended by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(R.string.loaded) }
    Text(
        text = stringResource(text),
        style = typography.header,
        color = background.textColor ,
        modifier = Modifier.clickable { expended = true }
    )
    DropdownMenu(
        expanded = expended,
        onDismissRequest = { expended = false },
        containerColor = background.primary
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    stringResource(R.string.loaded),
                    style = typography.textSize,
                    color = background.textColor
                )
            },
            onClick = {
                selectedCategory(PasswordType.LOADED)
                text = R.string.loaded
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    stringResource(R.string.generated),
                    style = typography.textSize,
                    color = background.textColor
                )
            },
            onClick = {
                selectedCategory(PasswordType.GENERATED)
                text = R.string.generated
            }
        )
    }
}