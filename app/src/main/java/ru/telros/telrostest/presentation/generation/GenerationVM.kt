package ru.telros.telrostest.presentation.generation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.telros.telrostest.domain.SaveFileUseCase
import ru.telros.telrostest.utils.FileOperationState
import ru.telros.telrostest.utils.PasswordType
import javax.inject.Inject
import kotlin.math.log2
import kotlin.math.round


@HiltViewModel
class GenerationVM @Inject constructor(
    private val saveFileUseCase: SaveFileUseCase
) : ViewModel() {

    private val _saveState = MutableStateFlow<FileOperationState?>(null)
    val saveState: StateFlow<FileOperationState?> = _saveState.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _selectedChars = MutableStateFlow<List<Char>>(emptyList())
    val selectedChars:StateFlow<List<Char>> = _selectedChars.asStateFlow()

    private val _entropy = MutableStateFlow(0.0)
    val entropy: StateFlow<Double> = _entropy.asStateFlow()

    private val _showAlert = MutableStateFlow(false)
    val showAlert: StateFlow<Boolean> = _showAlert.asStateFlow()

    fun loadFile(uri: Uri?) {
        _saveState.value = FileOperationState.Success("..Загрузка")
        viewModelScope.launch(Dispatchers.IO) {
            if (uri == null) return@launch
            saveFileUseCase.saveFile(uri).onSuccess {
                _saveState.value = FileOperationState.Success("Успешно сохранен")
            }.onFailure {
                _saveState.value = FileOperationState.Error("Ошибка сохранения")
            }
        }
    }

    fun savePassword(category: PasswordType) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_password.value.isEmpty()) return@launch
            val charset = _selectedChars.value.joinToString("")
            saveFileUseCase.savePassword(_password.value, category, _entropy.value, charSet = charset).onSuccess {
                _saveState.value = FileOperationState.Success("Успешно сохранен")
            }.onFailure {
                _saveState.value = FileOperationState.Error("Ошибка сохранения")
            }
        }
    }

    fun showAlert() {
        _showAlert.value = true
    }

    fun hideAlert() {
        _showAlert.value = false
    }

    fun generatePassword() {
        val password = (1.._selectedChars.value.size)
            .map { _selectedChars.value.random() }
            .joinToString("")
        _password.value = password
        calculateEntropy(password)
    }

    fun calculateEntropy(password: String) {
        val lowercase = ('a'..'z').toSet()
        val uppercase = ('A'..'Z').toSet()
        val digits = ('0'..'9').toSet()
        val special = setOf(
            '!',
            '@',
            '#',
            '$',
            '%',
            '^',
            '&',
            '*',
            '(',
            ')',
            '-',
            '+',
            '=',
            '[',
            ']',
            '{',
            '}',
            '|',
            ';',
            ':',
            '<',
            '>',
            '?',
            '/',
            '~',
            '`'
        )
        val usedCharset = mutableSetOf<Char>()
        password.forEach { char ->
            when (char) {
                in lowercase -> usedCharset.addAll(lowercase)
                in uppercase -> usedCharset.addAll(uppercase)
                in digits -> usedCharset.addAll(digits)
                in special -> usedCharset.addAll(special)
            }
        }
        val charsetSize = usedCharset.size
        val entropy = password.length * log2(charsetSize.toDouble())
        _entropy.value = round(entropy * 100) / 100

    }


    fun editPasswordSymbol(symbol: Char) {
        _selectedChars.value = _selectedChars.value.toMutableList().apply {
            if (contains(symbol)) {
                remove(symbol)
            } else {
                add(symbol)
            }
        }
    }

}