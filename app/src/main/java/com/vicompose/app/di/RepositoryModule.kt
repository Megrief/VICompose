package com.vicompose.app.di

import com.vicompose.core.entity.Image
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.repository.SearchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SEARCH_IMAGES = "searchImages"
val repositoryModule = module {

    factory<SearchUseCase<Image>>(named(SEARCH_IMAGES)) {
        SearchRepository(
            service = get()
        )
    }
}