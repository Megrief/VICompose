package com.vicompose.ui.elements.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.vicompose.R

@Composable
fun ImagePagerButtons(modifier: Modifier = Modifier, onClose: () -> Unit, openInWeb: ()-> Unit) {
    Row(
        modifier  = modifier
            .wrapContentWidth()
            .padding(bottom = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
        IconButton(
            modifier  = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                ),
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_exit_fullscreen),
                    contentDescription = "close"
                )
            },
            onClick = { onClose() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        IconButton(
            modifier  = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                ),
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_open_in_web),
                    contentDescription = "close"
                )
            },
            onClick = { openInWeb() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}