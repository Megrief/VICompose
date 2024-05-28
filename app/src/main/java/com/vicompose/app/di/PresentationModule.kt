package com.vicompose.app.di

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.vicompose.presetation.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(
            searchRepo = get(),

        )
    }
//
//    viewModel {
//        ImageViewModel(
//            searchRepo = get()
//        )
//    }

    factory<ConnectivityManager?> {
        ContextCompat.getSystemService(androidContext(), ConnectivityManager::class.java)
    }
}