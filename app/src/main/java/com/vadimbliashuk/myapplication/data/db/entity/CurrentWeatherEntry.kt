package com.vadimbliashuk.myapplication.data.db.entity


import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.vadimbliashuk.myapplication.data.db.entity.converter.ListConverter

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
    val cloudcover: Int,

    val feelslike: Double,

    val humidity: Int,

    @SerializedName("is_day")
    val isDay: String,

    @SerializedName("observation_time")
    val observationTime: String,

    val precip: Double,

    val pressure: Double,

    val temperature: Double,

    @SerializedName("uv_index")
    val uvIndex: Double,

    val visibility: Double,

    @SerializedName("weather_code")
    val weatherCode: Int,

    @SerializedName("weather_descriptions")
    @TypeConverters(ListConverter::class)
    val weatherDescriptions: List<String>,

    @SerializedName("weather_icons")
    @TypeConverters(ListConverter::class)
    val weatherIcons: List<String>,

    @SerializedName("wind_degree")
    val windDegree: Int,

    @SerializedName("wind_dir")
    val windDir: String,

    @SerializedName("wind_speed")
    val windSpeed: Double
)