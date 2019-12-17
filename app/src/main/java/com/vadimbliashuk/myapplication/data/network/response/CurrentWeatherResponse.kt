package com.vadimbliashuk.myapplication.data.network.response

import com.google.gson.annotations.SerializedName
import com.vadimbliashuk.myapplication.data.db.entity.CurrentWeatherEntry
import com.vadimbliashuk.myapplication.data.db.entity.Request
import com.vadimbliashuk.myapplication.data.db.entity.WeatherLocation


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    val request: Request
)