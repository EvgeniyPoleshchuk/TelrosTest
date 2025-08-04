package ru.telros.telrostest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FileEntity::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun fileDao(): FileDao

}