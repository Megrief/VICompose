package com.vicompose.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    onClose: (Int) -> Unit,
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
            val position = image?.position?.minus(1) ?: 0
            ImagePagerButtons(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClose = { onClose(position) },
                openInWeb = { openInWeb(image?.link ?: "") }
            )

        }
    }
}