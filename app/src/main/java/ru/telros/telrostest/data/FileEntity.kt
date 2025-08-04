package ru.telros.telrostest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.telros.telrostest.utils.PasswordType

@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "file_password")
    val password: List<String>,
    @ColumnInfo(name = "category")
    val category: PasswordType,
    @ColumnInfo(name = "entropy")
    val passwordEntropy: Double? = null,
    @ColumnInfo(name = "file_name")
    val fileName: String? = null, // Имя файла/папки для LOADED, null для GENERATED
    @ColumnInfo(name = "char_set")
    val charSet: String? = null
)
