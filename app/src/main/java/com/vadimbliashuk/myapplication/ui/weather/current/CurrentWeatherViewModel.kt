package com.vadimbliashuk.myapplication.ui.weather.current

import androidx.lifecycle.ViewModel
import com.vadimbliashuk.myapplication.data.repository.ForecastRepository
import com.vadimbliashuk.myapplication.internal.lazyDeffered

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    val weather by lazyDeffered {
        forecastRepository.getCurrentWeather() }
}
