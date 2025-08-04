package ru.telros.telrostest.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject


class ReadFile @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun readFileContent(uri: Uri): Pair<List<String>, String> {
        val passwords = mutableListOf<String>()
        withContext(Dispatchers.IO) {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    reader.forEachLine { line ->
                        passwords.addAll(line.split(",", " ").filter { it.isNotEmpty() })
                    }
                }
            }
        }
        val fileName = getFileNameFromUri(uri) ?: "unknown_file"
        return Pair(passwords, fileName)
    }

    private fun getFileNameFromUri(uri: Uri): String? {
        var fileName: String? = null
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    fileName = cursor.getString(nameIndex)
                }
            }
        }
        return fileName
    }
}