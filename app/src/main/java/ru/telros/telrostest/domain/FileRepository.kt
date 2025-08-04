package ru.telros.telrostest.domain


import kotlinx.coroutines.flow.Flow

import ru.telros.telrostest.domain.model.FileModel
import ru.telros.telrostest.utils.PasswordType

interface FileRepository {
    // Вставка новой записи
    suspend fun insert(file: FileModel)

    // Получение всех записей
    fun getAllFiles(): Flow<List<FileModel>>

    // Получение записей по категории (GENERATED или LOADED)
    fun getAllByCategory(category: PasswordType): Flow<List<FileModel>>

    // Получение уникальных имен папок (для загруженных файлов)
    fun getFolders(): Flow<List<String>>

    // Получение записи по имени файла (для отображения паролей в папке)
    fun getFileByName(fileName: String): Flow<FileModel?>

    // Удаление записи
    suspend fun delete(file: FileModel)

    // Обновление списка паролей в записи
    suspend fun updatePasswords(id: Int, newPasswords: List<String>)
}