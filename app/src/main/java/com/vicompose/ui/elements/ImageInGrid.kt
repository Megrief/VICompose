package com.vicompose.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.vicompose.R
import com.vicompose.core.entity.Image
import com.vicompose.ui.theme.ViComposeTheme

@Composable
fun ImageInGrid(modifier: Modifier = Modifier, image: Image?, onClick: () -> Unit = {}) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(image?.imageUrlThumb)
            .placeholder(drawableResId = R.drawable.ic_placeholder)
            .crossfade(true)
            .build()

        AsyncImage(
            model = imageRequest,
            contentDescription = image?.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            text = image?.title ?: "",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageInGridPreview() {
    val mockImage = Image(
        imageUrlFull = "https://academy.education.investing.com/wp-content/uploads/2022/09/Apple-offices.jpg",
        imageUrlThumb = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnmaODJMQyoRN_ciHqZkr-VRM2IeWKlzAJYt2TX8PEeUHgvOho&s",
        link = "https://www.linkedin.com/pulse/strategic-analysis-apple-inc-bidemi-ogedengbe",
        position = 1,
        title = "A Strategic Analysis of Apple Inc."
    )

    ViComposeTheme {
        Surface(modifier = Modifier.padding(0.dp)) {
            ImageInGrid(image = mockImage)
        }
    }
}

