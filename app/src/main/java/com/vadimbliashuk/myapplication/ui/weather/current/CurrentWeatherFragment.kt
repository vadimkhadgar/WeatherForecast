package com.vadimbliashuk.myapplication.ui.weather.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.vadimbliashuk.myapplication.R
import com.vadimbliashuk.myapplication.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            updateLocation("Brest")
            updateDateToToday()
            updateTemperature(it.temperature, it.feelslike)
            updateCondition(it.weatherDescriptions.toString())
            updateWind(it.windDir, it.windSpeed)
            updatePrecipitation(it.precip)
            updateVisibility(it.visibility)

            Glide.with(this@CurrentWeatherFragment)
                .load(it.weatherIcons[0])
                .into(iv_condition_icon)
        })
    }

    @Suppress("SameParameterValue")
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    @SuppressLint("SetTextI18n")
    private fun updateTemperature(temperature: Double, feelsLike: Double) {
        textView_temperature.text = "${temperature}${getString(R.string.celsius)}"
        textView_feels_like_temperature.text =
            "Feels Like ${feelsLike}${getString(R.string.celsius)}"
    }

    private fun updateCondition(weatherDescription: String) {
        textView_condition.text = weatherDescription.replace("[", "").replace("]", "")
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrecipitation(precipitationVolume: Double) {
        textView_precipitation.text = "Precipitation: $precipitationVolume mm"
    }

    @SuppressLint("SetTextI18n")
    private fun updateWind(windDirection: String, windSpeed: Double) {
        textView_wind.text = "Wind: $windDirection, $windSpeed kph"
    }

    @SuppressLint("SetTextI18n")
    private fun updateVisibility(visibilityDistance: Double) {
        textView_visibility.text = "Visibility: $visibilityDistance km"
    }
}


