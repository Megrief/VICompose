package com.vicompose.presetation

import androidx.paging.PagingData
import com.vicompose.core.entity.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val DEFAULT_QUERY: String  =  ""

data class UiState(
    val savedQuery: String = DEFAULT_QUERY,
    val imageFlow: Flow<PagingData<Image>> = flow { emit(PagingData.empty()) },
    val position: Int  = 0
)