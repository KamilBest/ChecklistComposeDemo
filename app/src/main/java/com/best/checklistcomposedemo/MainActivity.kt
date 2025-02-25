package com.best.checklistcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.best.checklistcomposedemo.ui.theme.ChecklistComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChecklistComposeDemoTheme {
                ScreenSwitcher()
            }
        }
    }
}

@Composable
fun ScreenSwitcher() {
    var selectedScreen by remember { mutableStateOf(ScreenType.COMPOSE) }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { selectedScreen = ScreenType.COMPOSE }) {
                    Text("Compose")
                }
                Button(onClick = { selectedScreen = ScreenType.WYSIWYG }) {
                    Text("WYSIWYG")
                }
            }

            when (selectedScreen) {
                ScreenType.COMPOSE -> CheckboxListScreen()
                ScreenType.WYSIWYG -> WysiwygCheckboxListScreen()
            }
        }
    }
}

enum class ScreenType {
    COMPOSE, WYSIWYG
}

