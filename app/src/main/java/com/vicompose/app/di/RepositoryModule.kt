package com.vicompose.app.di

import android.util.Log
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchUseCase> {
        Log.wtf("AAAAA", "repositoryModule")
        SearchRepository(
            service = get(),
            db = get(),
        )
    }
}