package com.vadimbliashuk.myapplication.data.db.entity.converter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun fromList(data: List<String>): String {
        return data.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        return data.split(",").toList()
    }
}