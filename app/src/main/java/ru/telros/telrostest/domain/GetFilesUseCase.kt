package ru.telros.telrostest.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import ru.telros.telrostest.presentation.dictionary.PasswordListItem
import ru.telros.telrostest.utils.PasswordType
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(
    private val repository: FileRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMainList(): Flow<List<PasswordListItem>> {
        return repository.getFolders().flatMapLatest { folders ->
            repository.getAllByCategory(PasswordType.GENERATED).map { generatedFiles ->
                buildList {
                    addAll(folders.map { PasswordListItem.Folder(it) })
                    generatedFiles.forEach { file ->
                        file.password.forEach { password ->
                            add(
                                PasswordListItem.PasswordItem(
                                    password = password,
                                    fileId = file.id,
                                    entropy = file.passwordEntropy,
                                    charSet = file.charSet,
                                    isGenerated = file.category == PasswordType.GENERATED
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun getPasswordsByFolder(fileName: String): Flow<List<PasswordListItem>> {
        return repository.getFileByName(fileName).map { file ->
            file?.password?.map { password ->
                PasswordListItem.PasswordItem(
                    password = password,
                    fileId = file.id,
                    entropy = file.passwordEntropy,
                    charSet = file.charSet,
                    isGenerated = file.category == PasswordType.GENERATED
                )
            } ?: emptyList()
        }
    }
}