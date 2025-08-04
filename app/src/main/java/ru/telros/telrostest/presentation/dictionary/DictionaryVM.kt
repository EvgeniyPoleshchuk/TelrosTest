package ru.telros.telrostest.presentation.dictionary

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.telros.telrostest.domain.DeletePasswordUseCase
import ru.telros.telrostest.domain.ExportPasswordsUseCase
import ru.telros.telrostest.domain.GetFilesUseCase
import ru.telros.telrostest.utils.PasswordType
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DictionaryVM @Inject constructor(
    private val getFilesUseCase: GetFilesUseCase,
    private val deletePasswordUseCase: DeletePasswordUseCase,
    private val exportPasswordsUseCase: ExportPasswordsUseCase
) : ViewModel() {
    private val _items = MutableStateFlow<List<PasswordListItem>>(emptyList())
    val items: StateFlow<List<PasswordListItem>> = _items.asStateFlow()

    private val _currentFolder = MutableStateFlow<String?>(null)
    val currentFolder: StateFlow<String?> = _currentFolder.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()


    init {
        loadMainList()
    }

    fun loadMainList() {
        viewModelScope.launch {
            getFilesUseCase.getMainList().collect { list ->
                _items.value = list
                _currentFolder.value = null
            }
        }
    }

    fun loadFolder(folder: String) {
        viewModelScope.launch {
            getFilesUseCase.getPasswordsByFolder(folder).collect { list ->
                _items.value = list
                _currentFolder.value = folder
            }
        }
    }

    fun deletePassword(fileId: Int, password: String) {
        viewModelScope.launch {
            deletePasswordUseCase.deletePassword(fileId, password).onSuccess {
                if (_currentFolder.value == null) {
                    loadMainList()
                } else {
                    loadFolder(_currentFolder.value!!)
                }
            }.onFailure { e ->
                _toastMessage.value = "Ошибка удаления: ${e.message}"
            }
        }
    }

    fun exportPasswords(context: Context, uri: Uri?) {
        viewModelScope.launch {
            if (uri == null || uri == Uri.EMPTY) {
                _toastMessage.value = "Выберите файл для экспорта"
                return@launch
            }
            exportPasswordsUseCase.exportPasswords(context, _currentFolder.value, uri).onSuccess {
                _toastMessage.value = "Экспортировано в выбранный файл"
            }.onFailure { e ->
                _toastMessage.value = "Ошибка экспорта: ${e.message}"
            }
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }
}

sealed class PasswordListItem {
    data class Folder(val name: String) : PasswordListItem()
    data class PasswordItem(
        val password: String,
        val fileId: Int,
        val entropy: Double?,
        val charSet: String?,
        val isGenerated: Boolean
    ) : PasswordListItem()
}