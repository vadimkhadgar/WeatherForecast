package com.vadimbliashuk.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.vadimbliashuk.myapplication.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}