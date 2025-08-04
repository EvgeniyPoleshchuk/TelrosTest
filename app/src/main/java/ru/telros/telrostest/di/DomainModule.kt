package ru.telros.telrostest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.telros.telrostest.domain.DeletePasswordUseCase
import ru.telros.telrostest.domain.ExportPasswordsUseCase
import ru.telros.telrostest.domain.FileRepository
import ru.telros.telrostest.domain.GetFilesUseCase
import ru.telros.telrostest.domain.SaveFileUseCase
import ru.telros.telrostest.utils.ReadFile

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideSaveFileUseCase(
        fileRepository: FileRepository,
        readFile: ReadFile
    ): SaveFileUseCase {
        return SaveFileUseCase(fileRepository, readFile)

    }

    @Provides
    fun provideGetFileUseCase(
        repository: FileRepository
    ): GetFilesUseCase {
        return GetFilesUseCase(repository)
    }

    @Provides
    fun provideDeletePasswordUseCase(
        repository: FileRepository
    ): DeletePasswordUseCase {
        return DeletePasswordUseCase(repository)
    }

    @Provides
    fun provideExportUseCase(
        repository: FileRepository
    ): ExportPasswordsUseCase {
        return ExportPasswordsUseCase(repository)
    }
}