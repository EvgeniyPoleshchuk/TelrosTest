package ru.telros.telrostest.presentation.generation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import ru.telros.telrostest.R
import ru.telros.telrostest.presentation.generation.ui_components.FilePicketButton
import ru.telros.telrostest.presentation.generation.ui_components.GenerationBlock
import ru.telros.telrostest.presentation.generation.ui_components.SaveZoneAlert
import ru.telros.telrostest.presentation.generation.ui_components.SettingsBottomSheet
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography
import ru.telros.telrostest.utils.FileOperationState

@Composable
fun GenerationScreen() {
    val viewModel: GenerationVM = hiltViewModel()
    var showSettings by remember { mutableStateOf(false) }
    val saveState = viewModel.saveState.collectAsState()
    val password = viewModel.password.collectAsState()
    val selectedChars = viewModel.selectedChars.collectAsState()
    val entropy = viewModel.entropy.collectAsState()
    val showAlert = viewModel.showAlert.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(saveState.value) {
        when (val state = saveState.value) {
            is FileOperationState.Error -> {
                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is FileOperationState.Loading -> {
                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is FileOperationState.Success -> {
                Toast.makeText(
                    context, state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            null -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimens.medium),
    ) {
        GenerationBlock(
            password.value,
            entropy.value,
            selectedChars.value.isNotEmpty(),
            onSave = { viewModel.showAlert() },
            onGenerate = { viewModel.generatePassword() }

        )
        Text(
            stringResource(R.string.selectedSymbol_title),
            style = typography.header.copy(textDecoration = TextDecoration.Underline),
            color = background.textColor,
            modifier = Modifier
                .padding(top = dimens.medium)
                .padding(horizontal = dimens.medium)
                .clickable {
                    showSettings = !showSettings
                })
        if (showSettings) {
            SettingsBottomSheet(
                selectedChars.value,
                onAddNewChar = { viewModel.editPasswordSymbol(it) }
            ) {
                showSettings = false
            }

        }
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimens.medium),
            contentAlignment = Alignment.Center
        ) {
            FilePicketButton {
                viewModel.loadFile(it)

            }
        }
        if (showAlert.value) {
            SaveZoneAlert(
                onSave = { category ->
                    viewModel.savePassword(category)
                    viewModel.hideAlert()
                },
                onDismiss = { viewModel.hideAlert() }
            )
        }
    }
}