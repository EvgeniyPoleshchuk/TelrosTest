package ru.telros.telrostest.presentation.generation.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.telros.telrostest.theme.Resources.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    selectedChars: List<Char>,
    onAddNewChar: (Char) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = Modifier.wrapContentSize(),
        sheetState = state,
        onDismissRequest = onDismiss,
        containerColor = background.primary
    ) {
        Column(Modifier.wrapContentHeight()) {
            SelectedCharactersBlock(
                selectedChars
            )
            CategoryPager(
                onAddNewChar
            )
        }
    }
}