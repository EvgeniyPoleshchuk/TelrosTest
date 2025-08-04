package ru.telros.telrostest.domain.model

import ru.telros.telrostest.utils.PasswordType

data class FileModel(
    val id: Int = 0,
    val password: List<String>,
    val category: PasswordType,
    val passwordEntropy: Double? = null,
    val fileName: String? = null,
    val charSet: String? = null
)