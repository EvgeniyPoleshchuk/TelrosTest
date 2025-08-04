package ru.telros.telrostest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.telros.telrostest.data.AppDataBase
import ru.telros.telrostest.data.FileRepositoryImpl
import ru.telros.telrostest.domain.FileRepository

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "file_database"
        ).build()

    @Provides
    fun provideFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository {
        return fileRepositoryImpl
    }
    @Provides
    fun provideFileDao(appDataBase: AppDataBase) = appDataBase.fileDao()

}