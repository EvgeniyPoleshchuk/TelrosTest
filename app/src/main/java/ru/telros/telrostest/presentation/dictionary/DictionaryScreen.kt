package ru.telros.telrostest.presentation.dictionary

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import ru.telros.telrostest.R
import ru.telros.telrostest.presentation.dictionary.ui_components.FolderItem
import ru.telros.telrostest.presentation.dictionary.ui_components.PasswordItem
import ru.telros.telrostest.presentation.generation.ui_components.ActionButton
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.dimens
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun DictionaryScreen() {
    val viewModel: DictionaryVM = hiltViewModel()
    val items by viewModel.items.collectAsState()
    val currentFolder by viewModel.currentFolder.collectAsState()
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val toastMessage by viewModel.toastMessage.collectAsState()
    val copySuccessText = stringResource(R.string.copySuccess)

    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        if (uri != null) {
            viewModel.exportPasswords(context, uri)
        }
    }

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.clearToastMessage()
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(dimens.medium)) {
        if (currentFolder != null) {
            ActionButton(R.string.backToList, Modifier.fillMaxWidth()) {
                viewModel.loadMainList()
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(dimens.small)
        ) {
            items(items) { item ->
                when (item) {
                    is PasswordListItem.Folder -> FolderItem(
                        folderName = item.name,
                        onClick = { viewModel.loadFolder(item.name) }
                    )

                    is PasswordListItem.PasswordItem -> PasswordItem(
                        passwordItem = item,
                        onCopy = {
                            clipboardManager.setText(AnnotatedString(item.password))
                            Toast.makeText(context, copySuccessText, Toast.LENGTH_SHORT).show()
                        },
                        onDelete = { viewModel.deletePassword(item.fileId, item.password) }
                    )
                }
            }
        }
        ActionButton(
            R.string.export_password,
            Modifier.fillMaxWidth()
        ) {
            exportLauncher.launch("exported_passwords.txt")
        }
    }
}




