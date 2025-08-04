package ru.telros.telrostest.presentation.dictionary.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun FolderItem(
    folderName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = dimens.small),
        shape = RoundedCornerShape(dimens.medium),
        colors = CardDefaults.cardColors(
            containerColor = background.cardColor
        )
    ) {
        Text(
            text = folderName,
            modifier = Modifier.padding(dimens.medium),
            style = typography.title,
            color = background.textColor
        )
    }
}