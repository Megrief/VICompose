package com.vicompose.ui

import androidx.compose.runtime.Composable
import com.vicompose.presetation.SearchViewModel
import com.vicompose.ui.elements.SearchScreenContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val viewModel: SearchViewModel = koinViewModel()

    SearchScreenContainer(searchViewModel = viewModel)
}