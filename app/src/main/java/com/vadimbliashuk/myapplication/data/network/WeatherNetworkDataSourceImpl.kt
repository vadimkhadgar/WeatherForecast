package com.vadimbliashuk.myapplication.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vadimbliashuk.myapplication.data.network.response.CurrentWeatherResponse
import com.vadimbliashuk.myapplication.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherstackApiService: WeatherstackApiService
) : WeatherNetworkDataSource {


    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = weatherstackApiService
                .getCurrentWeatherAsync(location)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException ) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}