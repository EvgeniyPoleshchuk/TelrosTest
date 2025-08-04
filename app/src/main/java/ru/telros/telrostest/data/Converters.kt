package ru.telros.telrostest.data

import androidx.room.TypeConverter


class Converters {

    @TypeConverter
    fun fromListToString(data: List<String>?): String {
        return data?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun fromStringToList(data: String?): List<String> {
        return data?.split(",")?.map { it.trim() } ?: emptyList()
    }
}