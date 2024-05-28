package com.vicompose.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vicompose.ui.theme.ViComposeTheme

@Composable
fun SearchField(modifier: Modifier = Modifier, onSearch: (String) -> Unit = { }, state: MutableState<String>) {
    var text by remember { state }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(16.dp),
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchFieldPreview() {
    ViComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

//            SearchField(modifier = Modifier, state = " ")
        }
    }
}