package com.vicompose.app.di

import com.vicompose.presetation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(
            searchRepo = get(named(SEARCH_IMAGES)),
        )
    }
}