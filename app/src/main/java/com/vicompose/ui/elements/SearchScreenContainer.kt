package com.vicompose.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.vicompose.core.entity.Image
import com.vicompose.ui.theme.ViComposeTheme

@Composable
fun SearchScreenContainer(
    modifier: Modifier = Modifier,
    navigate: (Int) -> Unit,
    images: LazyPagingItems<Image>,
    position: Int,
    onSearch: (String) -> Unit,
    searchState: String
) {

    Scaffold(
        modifier = modifier,
        topBar = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            SearchField(
                modifier = Modifier.padding(16.dp),
                state = searchState,
                onSearch = {
                    onSearch(it)
                }
            )
        }
    }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Column {
                GridImages(
                    modifier = Modifier,
                    images = images,
                    onClick = { index ->
                        navigate(index)
                    },
                    position = position
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenContainerPreview() {
    ViComposeTheme {
//        SearchScreenContainer()
    }
}