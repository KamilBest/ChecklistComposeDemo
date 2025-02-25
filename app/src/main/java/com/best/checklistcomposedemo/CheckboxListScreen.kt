package com.best.checklistcomposedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CheckboxListScreen() {
    var items by remember { mutableStateOf(emptyList<ListItem>()) }
    var nextId by remember { mutableIntStateOf(0) }

    Scaffold { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues, modifier = Modifier.fillMaxSize().background(Color.Black)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text("Compose Checkbox List", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable {
                            items = listOf(ListItem(nextId++, "New Item $nextId")) + items
                        },
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add Item",
                        tint = Color.White
                    )
                }
            }
            items(items, key = { it.id }) { item ->
                CheckboxListItem(item = item,
                    onRemove = { items = items.filter { it.id != item.id } })
            }
        }
    }
}

@Composable
fun CheckboxListItem(item: ListItem, onRemove: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(item.text) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        Spacer(modifier = Modifier.width(8.dp))
        TextField(modifier = Modifier.weight(1f),
            value = textFieldValue,
            onValueChange = { textFieldValue = it })
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier.clickable(onClick = onRemove),
            imageVector = Icons.Default.Delete,
            contentDescription = "Remove Item",
        )
    }
}
