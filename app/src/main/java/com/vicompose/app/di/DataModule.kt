package com.vicompose.app.di

import androidx.room.Room
import com.vicompose.data.network.service.SerperApiService
import com.vicompose.data.room.db.VeryInterestingDb
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ACCESS_TOKEN = "becd76ebe912ab28d2bb64715ce960d11fcc1a87"
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
                            .addHeader("X-API-KEY", ACCESS_TOKEN)
                            .build()
                    )
                }
            }.build()
    }

    single<SerperApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(SerperApiService::class.java)
    }

    single<VeryInterestingDb> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = VeryInterestingDb::class.java,
            name = "very_interesting.db"
        ).build()
    }
}