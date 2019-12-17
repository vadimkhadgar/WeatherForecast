package com.vadimbliashuk.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.vadimbliashuk.myapplication.data.db.entity.CurrentWeatherEntry
import com.vadimbliashuk.myapplication.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}