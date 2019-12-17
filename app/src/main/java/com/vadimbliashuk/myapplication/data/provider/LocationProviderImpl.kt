package com.vadimbliashuk.myapplication.data.provider

import com.vadimbliashuk.myapplication.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Brest"
    }
}