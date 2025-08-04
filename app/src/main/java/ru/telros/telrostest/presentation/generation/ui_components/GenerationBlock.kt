package ru.telros.telrostest.presentation.generation.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.telros.telrostest.R
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography


@Composable
fun GenerationBlock(
    password: String,
    entropy: Double,
    enableButton: Boolean,
    onSave: () -> Unit,
    onGenerate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimens.medium),
    ) {
        Text(
            "Сгенерированный пароль",
            style = typography.header,
            color = background.textColor
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens.medium, bottom = dimens.small),
            colors = CardDefaults.cardColors(
                containerColor = background.cardColor
            )
        ) {
            Text(
                modifier = Modifier.padding(dimens.medium),
                text = password,
                style = typography.header,
                color = background.textColor
            )
        }
        Text(
            "Энтропия: $entropy",
            style = typography.header,
            color = background.textColor,
        )
        Spacer(modifier = Modifier.padding(bottom = dimens.medium))
        ActionButtonRow(
            save = onSave,
            generate = onGenerate,
            enable = enableButton
        )
    }

}

@Composable
fun ActionButtonRow(
    generate: () -> Unit,
    save: () -> Unit,
    enable: Boolean
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ActionButton(
                R.string.generation,
                modifier = Modifier.weight(1f),
                enable,
                generate,
            )
            Spacer(modifier = Modifier.padding(horizontal = dimens.extraSmall))
            ActionButton(
                R.string.save,
                modifier = Modifier.weight(1f),
                enable,
                save
            )
        }
    }

}

@Composable
fun ActionButton(title: Int, modifier: Modifier, enable: Boolean = true, action: () -> Unit) {
    Button(
        action,
        modifier = modifier,
        enabled = enable,
        shape = RoundedCornerShape(dimens.medium),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1A74D2),
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(title),
            style = typography.title,
            color = background.secondary
        )

    }

}