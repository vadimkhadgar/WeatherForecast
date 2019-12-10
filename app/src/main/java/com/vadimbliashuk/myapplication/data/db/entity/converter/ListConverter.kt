package com.vadimbliashuk.myapplication.data.db.entity.converter

import androidx.room.TypeConverter
import java.util.*


class ListConverter {

    @TypeConverter
    fun fromList(data: List<String?>): String {
        return data.joinToString(separator = ",")
    }

//    @TypeConverter
//    fun toList(data: String): List<String> {
//        return data.split(",").toList()
//    }
}