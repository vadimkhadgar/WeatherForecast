package com.vadimbliashuk.myapplication

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vadimbliashuk.myapplication.data.db.ForecastDatabase
import com.vadimbliashuk.myapplication.data.network.*
import com.vadimbliashuk.myapplication.data.repository.ForecastRepository
import com.vadimbliashuk.myapplication.data.repository.ForecastRepositoryImpl
import com.vadimbliashuk.myapplication.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind().from(singleton { ForecastDatabase(instance()) })
        bind().from(singleton { instance<ForecastDatabase>().currentWeatherDao() })
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind().from(singleton { WeatherstackApiService(instance()) })
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind().from( provider { CurrentWeatherViewModelFactory(instance()) })
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}