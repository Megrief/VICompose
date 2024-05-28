package com.vicompose.app

import android.app.Application
import com.vicompose.app.di.dataModule
import com.vicompose.app.di.presentationModule
import com.vicompose.app.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VIComposeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, repositoryModule, presentationModule)
            androidContext(this@VIComposeApp)
        }
    }
}