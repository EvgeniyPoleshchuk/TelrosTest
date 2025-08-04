package ru.telros.telrostest.presentation.generation.ui_components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.telros.telrostest.R


@Composable
fun FilePicketButton(onSelectedFile:(Uri?)->Unit){

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {uri ->
            onSelectedFile(uri)
        }
    )
    ActionButton(R.string.loadFromStorage, Modifier) {
        launcher.launch("text/plain")
    }
}