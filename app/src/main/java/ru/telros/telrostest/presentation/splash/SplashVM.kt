package ru.telros.telrostest.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.telros.telrostest.data.AppDataBase
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val appDatabase: AppDataBase
) : ViewModel() {

    private val _isDatabaseInitialized = MutableStateFlow(false)
    val isDatabaseInitialized: StateFlow<Boolean> = _isDatabaseInitialized

    init {
        checkDatabaseInitialization()
    }

    private fun checkDatabaseInitialization() {
        viewModelScope.launch {
            try {
                val result = appDatabase.fileDao().getAllFiles().first()
                _isDatabaseInitialized.value = true
            } catch (e: Exception) {
                _isDatabaseInitialized.value = false
                Log.e("SplashVM", "Ошибка при работе с базой данных: ${e.message}")
            }
        }
    }
}