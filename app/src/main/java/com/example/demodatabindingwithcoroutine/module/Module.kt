package com.example.demodatabindingwithcoroutine.module

import android.app.Application
import com.example.demodatabindingwithcoroutine.data.api.ApiHelper
import com.example.demodatabindingwithcoroutine.ui.home.HomeRepository
import com.example.demodatabindingwithcoroutine.ui.home.HomeViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val retrofitServiceModule = module {
    fun provideUserService(retrofit: Retrofit): ApiHelper {
        return retrofit.create(ApiHelper::class.java)
    }

    single { provideUserService(get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
}

val repositoryModule = module {
    fun provideHomeRepository(api: ApiHelper): HomeRepository {
        return HomeRepository(api)
    }

    single { provideHomeRepository(get()) }
}