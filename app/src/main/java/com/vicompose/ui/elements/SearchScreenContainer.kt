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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vicompose.core.entity.Image
import com.vicompose.presetation.SearchViewModel
import com.vicompose.ui.theme.ViComposeTheme

@Composable
fun SearchScreenContainer(modifier: Modifier = Modifier, searchViewModel: SearchViewModel) {

    val collected: LazyPagingItems<Image> = searchViewModel.imageFlow.value.collectAsLazyPagingItems()
    val context = LocalContext.current
    var pagerState by remember {
        mutableStateOf(false)
    }

    var position by remember {
        mutableIntStateOf(0)
    }

    if(pagerState) {
        Surface(modifier = Modifier.fillMaxSize()) {
            ImagePager(
                images = collected,
                index = position,
                onClose  =  {
                    position = it
                    pagerState  = false
                },
                openInWeb = { searchViewModel.openInWeb(context, it) }
            )
        }
    } else {
        Scaffold(modifier = modifier,
            topBar = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    SearchField(
                        modifier = Modifier.padding(16.dp),
                        state = remember { searchViewModel.savedQuery } ,
                        onSearch = {
                            searchViewModel.search(it)
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
                        images = collected,
                        onClick = {   index ->
                            position = index
                            pagerState = true
                                  },
                        position = position
                    )
                }
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