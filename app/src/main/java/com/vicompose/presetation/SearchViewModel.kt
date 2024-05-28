package com.vicompose.presetation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vicompose.core.entity.Image
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.util.toImage
import com.vicompose.presetation.util.debounce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepo: SearchUseCase,
) : ViewModel() {

    var savedQuery: MutableState<String> = mutableStateOf(DEFAULT_QUERY)

    val imageFlow: MutableState<Flow<PagingData<Image>>> = mutableStateOf(flow { emit(PagingData.empty()) })

    var position: MutableIntState = mutableIntStateOf(0)

    private var lastQuery: String? = null

    private val searchRequest: (String) -> Unit = debounce(SEARCH_DELAY, viewModelScope, true) { query ->
        savedQuery.value  = query
        viewModelScope.launch(Dispatchers.IO) {
            if (query != lastQuery) {
                lastQuery = query
                imageFlow.value = searchRepo.search(query = query)
                    .map { it.map { dto -> dto.toImage() } }
                    .cachedIn(viewModelScope)
            }
        }
    }

    fun search(query: String) = searchRequest(query)

    fun openInWeb(context: Context, url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
        const val DEFAULT_QUERY: String  =  ""
    }
}