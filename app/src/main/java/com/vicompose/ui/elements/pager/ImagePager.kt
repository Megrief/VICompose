package com.vicompose.ui.elements.pager

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.vicompose.R
import com.vicompose.core.entity.Image

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<Image>,
    index: Int,
    navigate: (Int) -> Unit,
    openInWeb: (String) -> Unit
) {
    HorizontalPager(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = rememberPagerState(initialPage = index) { images.itemCount }) { pos ->

        Box(modifier = Modifier.fillMaxSize()) {
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(images[pos]?.imageUrlFull)
                .placeholder(drawableResId = R.drawable.ic_placeholder)
                .crossfade(true)
                .build()
            AsyncImage(
                model = imageRequest,
                contentDescription  = images[pos]?.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            )

            val image = images[pos]
            val position = image?.position ?: 0

            var handleBackPressed = remember { true }
            BackHandler(enabled = handleBackPressed) {
                navigate(position)
                handleBackPressed = false
            }

            ImagePagerButtons(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClose = { navigate(position) },
                openInWeb = { openInWeb(image?.link ?: "") }
            )

        }
    }
}