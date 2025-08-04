package ru.telros.telrostest.domain

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeletePasswordUseCase @Inject constructor(
    private val repository: FileRepository
) {
    suspend fun deletePassword(fileId: Int, password: String): Result<Boolean> {
        return try {
            val file = repository.getAllFiles().first().find { it.id == fileId }
            if (file != null) {
                val newPasswords = file.password.filter { it != password }
                if (newPasswords.isEmpty()) {
                    repository.delete(file)
                } else {
                    repository.updatePasswords(fileId, newPasswords)
                }
                Result.success(true)
            } else {
                Result.failure(Exception("Файл не найден"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}