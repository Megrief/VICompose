package com.vicompose.ui.elements

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.vicompose.core.entity.Image
import com.vicompose.ui.theme.ViComposeTheme

@Composable
fun ImagePagerContainer(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<Image>,
    navigate:  (Int) -> Unit,
    onOpenInWeb: (Context, String) -> Unit,
    position: Int
) {
    val context = LocalContext.current



    Surface(modifier = Modifier.fillMaxSize()) {
        ImagePager(
            modifier = modifier,
            images = images,
            index = position,
            navigate = {
                navigate(position)
            },
            openInWeb = { onOpenInWeb(context, it) }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImagePagerContainerPreview(){
    ViComposeTheme {
//        ImagePagerContainer( )
    }
}