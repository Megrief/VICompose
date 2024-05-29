package com.vicompose.app.di

import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.repository.SearchRepository
import com.vicompose.data.room.dto.ImageDto
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SEARCH_IMAGES = "searchImages"
val repositoryModule = module {

    factory<SearchUseCase<ImageDto>>(named(SEARCH_IMAGES)) {
        SearchRepository(
            service = get(),
            db = get(),
        )
    }
}