package ru.telros.telrostest.data


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.telros.telrostest.domain.FileRepository
import ru.telros.telrostest.domain.model.FileModel
import ru.telros.telrostest.utils.PasswordType
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileDao: FileDao
) : FileRepository {
    override suspend fun insert(file: FileModel) {
        fileDao.insert(file.toEntity())
    }

    override fun getAllFiles(): Flow<List<FileModel>> {
        return fileDao.getAllFiles().map { it.map { entity -> entity.toFileModel() } }
    }

    override fun getAllByCategory(category: PasswordType): Flow<List<FileModel>> {
        return fileDao.getAllByCategory(category).map { it.map { entity -> entity.toFileModel() } }
    }

    override fun getFolders(): Flow<List<String>> {
        return fileDao.getFolders()
    }

    override fun getFileByName(fileName: String): Flow<FileModel?> {
        return fileDao.getFileByName(fileName).map { it?.toFileModel() }
    }

    override suspend fun delete(file: FileModel) {
        fileDao.delete(file.toEntity())
    }

    override suspend fun updatePasswords(id: Int, newPasswords: List<String>) {
        fileDao.updatePasswords(id, newPasswords)
    }
}