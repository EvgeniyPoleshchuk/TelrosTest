package ru.telros.telrostest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.telros.telrostest.utils.PasswordType

@Dao
interface FileDao {
    @Insert
    suspend fun insert(file: FileEntity)

    @Query("SELECT * FROM files")
    fun getAllFiles(): Flow<List<FileEntity>>

    @Query("SELECT * FROM files WHERE category = :category")
    fun getAllByCategory(category: PasswordType): Flow<List<FileEntity>>

    @Query("SELECT file_name FROM files WHERE file_name IS NOT NULL")
    fun getFolders(): Flow<List<String>>

    @Query("SELECT * FROM files WHERE file_name = :fileName")
    fun getFileByName(fileName: String): Flow<FileEntity?>

    @Delete
    suspend fun delete(file: FileEntity)

    @Query("UPDATE files SET file_password = :newPasswords WHERE id = :id")
    suspend fun updatePasswords(id: Int, newPasswords: List<String>)

    @Update
    suspend fun update(file: FileEntity)
}