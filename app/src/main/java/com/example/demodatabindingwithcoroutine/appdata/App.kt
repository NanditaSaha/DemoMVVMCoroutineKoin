package com.example.demodatabindingwithcoroutine.appdata

import android.app.Application
import com.example.demodatabindingwithcoroutine.module.netModule
import com.example.demodatabindingwithcoroutine.module.repositoryModule
import com.example.demodatabindingwithcoroutine.module.retrofitServiceModule
import com.example.demodatabindingwithcoroutine.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelModule)
            modules(retrofitServiceModule)
            modules(repositoryModule)
            modules(netModule)
        }
    }
}