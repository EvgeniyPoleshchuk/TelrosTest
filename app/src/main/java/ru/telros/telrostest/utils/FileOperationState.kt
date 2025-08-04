package ru.telros.telrostest.utils

sealed interface FileOperationState {
    data class Loading(val message: String) : FileOperationState
    data class Success(val message: String) : FileOperationState
    data class Error(val message: String) : FileOperationState

}