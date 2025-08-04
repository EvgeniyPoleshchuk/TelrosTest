package ru.telros.telrostest.data

import ru.telros.telrostest.domain.model.FileModel

fun FileEntity.toFileModel() = FileModel(
    id = id,
    password = password,
    category = category,
    passwordEntropy = passwordEntropy,
    fileName = fileName,
    charSet = charSet
)

fun FileModel.toEntity() = FileEntity(
    id = id,
    password = password,
    category = category,
    passwordEntropy = passwordEntropy,
    fileName = fileName,
    charSet = charSet
)