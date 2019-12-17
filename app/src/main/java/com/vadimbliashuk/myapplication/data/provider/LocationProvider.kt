package com.vadimbliashuk.myapplication.data.provider

import com.vadimbliashuk.myapplication.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}