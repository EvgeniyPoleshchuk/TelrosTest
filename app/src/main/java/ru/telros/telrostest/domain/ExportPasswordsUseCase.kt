package ru.telros.telrostest.domain

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.telros.telrostest.utils.PasswordType
import javax.inject.Inject

class ExportPasswordsUseCase @Inject constructor(
    private val repository: FileRepository
) {
    suspend fun exportPasswords(
        context: Context,
        currentFolder: String?,
        uri: Uri
    ): Result<Boolean> {
        return try {
            val passwords = if (currentFolder == null) {
                repository.getAllByCategory(PasswordType.GENERATED).first().flatMap { it.password }
            } else {
                repository.getFileByName(currentFolder).first()?.password ?: emptyList()
            }
            val content = passwords.joinToString("\n")
            withContext(Dispatchers.IO) {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(content.toByteArray())
                }
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}