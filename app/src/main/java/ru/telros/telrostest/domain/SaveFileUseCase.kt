package ru.telros.telrostest.domain

import android.net.Uri
import ru.telros.telrostest.domain.model.FileModel
import ru.telros.telrostest.utils.PasswordType
import ru.telros.telrostest.utils.ReadFile

import javax.inject.Inject

class SaveFileUseCase @Inject constructor(
    private val repository: FileRepository,
    private val readFile: ReadFile
) {
    suspend fun saveFile(file: Uri): Result<Boolean> {
        return try {
            val (passwords, fileName) = readFile.readFileContent(file)
            val fileModel = FileModel(
                password = passwords,
                category = PasswordType.LOADED,
                fileName = fileName
            )
            repository.insert(fileModel)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun savePassword(
        password: String,
        category: PasswordType,
        entropy: Double,
        charSet: String? = null
    ): Result<Boolean> {
        return try {
            val passwordToList = listOf(password)
            val fileModel = FileModel(
                password = passwordToList,
                category = category,
                passwordEntropy = entropy,
                charSet = charSet
            )
            repository.insert(fileModel)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}