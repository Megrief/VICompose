package com.vicompose.presetation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.room.dto.ImageDto
import com.vicompose.util.toImage
import com.vicompose.util.debounce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepo: SearchUseCase<ImageDto>,
) : ViewModel() {

    private val _uiState: MutableState<UiState> = mutableStateOf(UiState())
    val uiState: State<UiState> get() = _uiState

    private val searchRequest: (String) -> Unit = debounce(SEARCH_DELAY, viewModelScope, true) { query ->
        viewModelScope.launch(Dispatchers.IO) {
            if (query != uiState.value.savedQuery && query.isNotBlank()) {
                val imageFlow = searchRepo.search(query = query)
                    .map { it.map { dto -> dto.toImage() } }
                    .cachedIn(viewModelScope)
                _uiState.value = uiState.value.copy(
                    imageFlow = imageFlow,
                    savedQuery = query
                )
            }
        }
    }

    fun search(query: String) = searchRequest(query)

    fun openInWeb(context: Context, url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun onNavigate(position: Int) {
        _uiState.value = uiState.value.copy(position = position)
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}