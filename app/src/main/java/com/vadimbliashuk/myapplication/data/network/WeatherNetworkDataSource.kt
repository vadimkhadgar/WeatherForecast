package com.vadimbliashuk.myapplication.data.network

import androidx.lifecycle.LiveData
import com.vadimbliashuk.myapplication.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location:String
    )
}