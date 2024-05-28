package com.vicompose.app.di

import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchUseCase> {
        SearchRepository(
            service = get(),
            db = get(),
        )
    }
}