package com.vicompose.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.vicompose.core.entity.Image

@Composable
fun GridImages(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<Image>,
    onClick: (Int) -> Unit = { },
    position: Int
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement  = Arrangement.spacedBy(8.dp),
        state = rememberLazyGridState(initialFirstVisibleItemIndex = position + 1)
    ) {
        items(count = images.itemCount) { index ->
            ImageInGrid(
                modifier = Modifier,
                image = images[index],
                onClick  =  { onClick(images[index]?.position?.minus(1) ?: 0) })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GridImagesPreview() {

}