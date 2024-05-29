package com.vicompose.app.di

import com.example.vicompose.BuildConfig
import com.vicompose.data.network.service.SerperApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://google.serper.dev"

val dataModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("X-API-KEY", BuildConfig.SERPER_API_TOKEN)
                            .build()
                    )
                }
            }.build()
    }

    single<SerperApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(SerperApiService::class.java)
    }
}