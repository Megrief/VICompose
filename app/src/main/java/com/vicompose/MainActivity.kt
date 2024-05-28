package com.vicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vicompose.ui.MainScreen
import com.vicompose.ui.theme.ViComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViComposeTheme {
                MainScreen()
            }
        }
    }
}